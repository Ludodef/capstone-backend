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
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
//crea un costruttore usando tutte variabili final
@RequiredArgsConstructor
public class LibroService {

    private final LibroRepository repository;
    private final AutoreRepository autoreRepository;
    private final SagaRepository sagaRepository;
    private final CasaEditriceRepository casaEditriceRepository;
    private final GenereRepository genereRepository;


    public List<Libro> findAll(){
        return repository.findAll();
    }

    //le relazioni molti a molti funzionano lo stesso
@Transactional
    public CompleteResponse findbyId(Long id){
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
        it.epicode.shop_hobby.libri.saghe.Response sagheResponse = new it.epicode.shop_hobby.libri.saghe.Response();
        BeanUtils.copyProperties(entity.getSaga(), sagheResponse);
        completeResponse.setAutore(autoreResponse);
        completeResponse.setCasaEditrice(casaEditriceResponse);
        completeResponse.setSaga(sagheResponse);
        return completeResponse;
    }

    public Response create(Request request){
        if(!autoreRepository.existsById(request.getIdAutore())){
            throw new EntityNotFoundException("Autore non trovato");
        }
        if (!sagaRepository.existsById(request.getIdSaga())){
            throw new EntityNotFoundException("Saga non trovata");
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
        entity.setGeneri(genere);
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
        if (!sagaRepository.existsById(request.getIdSaga())){
            throw new EntityNotFoundException("Saga non trovata");
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
        entity.setGeneri(genere);
        entity.setSaga(saga);
        repository.save(entity);
        Response response = new Response();
        BeanUtils.copyProperties(entity, response);
        return response;
    }
//reucupero di una lista di libri per autore
    public List<Libro> findByAutore(Autore autore) {
        return repository.findByAutore(autore);

    }
    //per casa editrice
    public List<Libro> findByCasaEditrice(CasaEditrice casaEditrice) {
        return repository.findByCasaEditrice(casaEditrice);

    }
    public List<Libro> findBySaga(Saga saga) {
        return repository.findBySaga(saga);
    }
    public Libro findByTitolo(String titolo){
        return repository.findByTitolo(titolo);
    }

    public String delete(Long id){
        if(!repository.existsById(id)){
            throw new EntityNotFoundException("Libro non trovato");
        }
        repository.deleteById(id);
        return "Libro eliminato con successo";
    }
}
