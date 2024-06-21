package it.epicode.shop_hobby.libri.libro;


import it.epicode.shop_hobby.libri.autore.Autore;
import it.epicode.shop_hobby.libri.autore.AutoreRepository;
import it.epicode.shop_hobby.libri.casa_editrice.CasaEditrice;
import it.epicode.shop_hobby.libri.casa_editrice.CasaEditriceRepository;
import it.epicode.shop_hobby.libri.genere.Genere;
import it.epicode.shop_hobby.libri.genere.GenereRepository;
import it.epicode.shop_hobby.libri.saghe.Saga;
import it.epicode.shop_hobby.libri.saghe.SagaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibroService {

    @Autowired
    private LibroRepository repository;
    @Autowired
    private AutoreRepository autoreRepository;
    @Autowired
    private SagaRepository sagaRepository;
    @Autowired
    private CasaEditriceRepository casaEditriceRepository;
    @Autowired
    private GenereRepository genereRepository;


    public List<Libro> findAll(){
        return repository.findAll();
    }

    public Response findbyId(Long id){
        if(!repository.existsById(id)){
            throw new EntityNotFoundException("Libro non trovato");
        }
        Libro entity = repository.findById(id).get();
        CompleteResponse completeResponse = new CompleteResponse();
        BeanUtils.copyProperties(entity, completeResponse);
        completeResponse.setGenere(entity.getGeneri());
        it.epicode.shop_hobby.libri.autore.Response autoreResponse = new it.epicode.shop_hobby.libri.autore.Response();
        BeanUtils.copyProperties(entity.getAutore(), autoreResponse);
        it.epicode.shop_hobby.libri.casa_editrice.Response casaEditriceResponse = new it.epicode.shop_hobby.libri.casa_editrice.Response();
        BeanUtils.copyProperties(entity.getCasaEditrice(), casaEditriceResponse);
        completeResponse.setAutore(autoreResponse);
        completeResponse.setCasaEditrice(casaEditriceResponse);

        return completeResponse;
    }

    public Response create(Request request){
        if(!autoreRepository.existsById(request.getIdAutore())){
            throw new EntityNotFoundException("Autore non trovato");
        }
        if(!casaEditriceRepository.existsById(request.getIdCasaEditrice())){
            throw new EntityNotFoundException("Casa editrice non trovata");
        }

        Libro entity = new Libro();
        BeanUtils.copyProperties(request,entity);
        Autore autore = autoreRepository.findById(request.getIdAutore()).get();
        CasaEditrice casaEditrice = casaEditriceRepository.findById(request.getIdCasaEditrice()).get();
        List<Genere> genere = genereRepository.findAllById(request.getIdGenere());
        Saga saga = sagaRepository.findById(request.getIdSaga()).get();
        entity.setAutore(autore);
        entity.setCasaEditrice(casaEditrice);
        entity.setGenere(genere);
        entity.setSaga(saga);
        Response response = new Response();
        BeanUtils.copyProperties(entity, response);
        repository.save(entity);
        return response;
    }

    public Response modify(Long id, Request request){
        if(!autoreRepository.existsById(request.getIdAutore())){
            throw new EntityNotFoundException("Autore non trovato");
        }
        if(!casaEditriceRepository.existsById(request.getIdCasaEditrice())){
            throw new EntityNotFoundException("Casa editrice non trovata");
        }
        if(!repository.existsById(id)){
            throw new EntityNotFoundException("Libro non trovato");
        }
        Libro entity = repository.findById(id).get();
        Autore autore = autoreRepository.findById(request.getIdAutore()).get();
        CasaEditrice casaEditrice = casaEditriceRepository.findById(request.getIdCasaEditrice()).get();
        List<Genere> genere = genereRepository.findAllById(request.getIdGenere());
        Saga saga = sagaRepository.findById(request.getIdSaga()).get();

        BeanUtils.copyProperties(request,entity);
        entity.setAutore(autore);
        entity.setCasaEditrice(casaEditrice);
        entity.setGenere(genere);
        entity.setSaga(saga);
        repository.save(entity);
        Response response = new Response();
        BeanUtils.copyProperties(entity, response);
        return response;
    }

    public String delete(Long id){
        if(!repository.existsById(id)){
            throw new EntityNotFoundException("Libro non trovato");
        }
        repository.deleteById(id);
        return "Libro eliminato con successo";
    }
}
