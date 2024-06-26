package it.epicode.shop_libri.libri_e_manga.cartacei;

import it.epicode.shop_libri.libri_e_manga.autori.Autore;
import it.epicode.shop_libri.libri_e_manga.autori.AutoreRepository;
import it.epicode.shop_libri.libri_e_manga.case_editrici.CasaEditrice;
import it.epicode.shop_libri.libri_e_manga.case_editrici.CasaEditriceRepository;
import it.epicode.shop_libri.libri_e_manga.genere.Genere;
import it.epicode.shop_libri.libri_e_manga.genere.GenereRepository;
import it.epicode.shop_libri.libri_e_manga.saga.Saga;
import it.epicode.shop_libri.libri_e_manga.saga.SagaRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartaceoService {
    @Autowired
    private CartaceoRepository repository;
    @Autowired
    private GenereRepository genereRepository;
    @Autowired
    private CasaEditriceRepository casaEditriceRepository;
    @Autowired
    private AutoreRepository autoreRepository;
    @Autowired
    private SagaRepository sagaRepository;

    //POST
    @Transactional
    public Response create(Request request){
        if (!autoreRepository.existsById(request.getIdAutore())){
            throw new EntityNotFoundException("Autore not found");
        }
        if (!casaEditriceRepository.existsById(request.getIdCasaEditrice())){
            throw new EntityNotFoundException("Casa Editrice not found");
        }
        if (!sagaRepository.existsById(request.getIdSaga())){
            throw  new EntityNotFoundException("Saga not found");
        }
        Cartaceo entity = new Cartaceo();
        BeanUtils.copyProperties(request, entity);
        Autore autore = autoreRepository.findById(request.getIdAutore()).get();
        CasaEditrice casaEditrice = casaEditriceRepository.findById(request.getIdCasaEditrice()).get();
        List<Genere> categorie = genereRepository.findAllById(request.getIdGeneri());
        Saga saga = sagaRepository.findById(request.getIdSaga()).get();
        entity.setAutore(autore);
        entity.setCasaEditrice(casaEditrice);
        entity.setGenere(categorie);
        entity.setSaga(saga);
        Response response = new Response();
        repository.save(entity);
        BeanUtils.copyProperties(entity, response);
        return response;

    }

    //PUT
    public Response modify(Long id, Request request){
        if(!autoreRepository.existsById(request.getIdAutore())){
            throw new EntityNotFoundException("Autore not found");
        }
        if(!casaEditriceRepository.existsById(request.getIdCasaEditrice())){
            throw  new EntityNotFoundException("Casa editrice not found");
        }
        if(!sagaRepository.existsById(request.getIdSaga())){
            throw new EntityNotFoundException("Saga not found");
        }
        if(!repository.existsById(id)){
            throw new EntityNotFoundException("Libro not found");
        }
        Cartaceo entity = repository.findById(id).get();
        Autore autore = autoreRepository.findById(request.getIdAutore()).get();
        CasaEditrice casaEditrice = casaEditriceRepository.findById(request.getIdCasaEditrice()).get();
        List<Genere> categorie = genereRepository.findAllById(request.getIdGeneri());
        Saga saga = sagaRepository.findById(request.getIdSaga()).get();
        BeanUtils.copyProperties(request, entity);
        entity.setAutore(autore);
        entity.setCasaEditrice(casaEditrice);
        entity.setGenere(categorie);
        entity.setSaga(saga);
        repository.save(entity);
        Response response = new Response();
        BeanUtils.copyProperties(entity, response);
        return response;
    }

    public List<CartaceoResponsePrj> findAll(){
        return repository.findAllBy();
    }

    @Transactional
    public CompleteResponse findById(Long id){
        if(!repository.existsById(id)){
            throw new EntityNotFoundException("Libro not found");
        }
        Cartaceo entity = repository.findById(id).get();
        CompleteResponse completeResponse = new CompleteResponse();
        BeanUtils.copyProperties(entity, completeResponse);
        completeResponse.setGenere(entity.getGenere());
        it.epicode.shop_libri.libri_e_manga.autori.Response autoreResponse = new it.epicode.shop_libri.libri_e_manga.autori.Response();
        BeanUtils.copyProperties(entity.getAutore(), autoreResponse);
        it.epicode.shop_libri.libri_e_manga.case_editrici.Response casaEditriceResponse = new it.epicode.shop_libri.libri_e_manga.case_editrici.Response();
        BeanUtils.copyProperties(entity.getCasaEditrice(), casaEditriceResponse);
        it.epicode.shop_libri.libri_e_manga.saga.Response sagaResponse = new it.epicode.shop_libri.libri_e_manga.saga.Response();
        BeanUtils.copyProperties(entity.getSaga(), sagaResponse);
        completeResponse.setAutore(autoreResponse);
        completeResponse.setCasaEditrice(casaEditriceResponse);
        completeResponse.setSaga(sagaResponse);
        return completeResponse;

    }

    //GET
    public List<Cartaceo> findByAutore(Autore autore){
        return repository.findByAutore(autore);
    }

    public List<Cartaceo> findByCasaEditrice(CasaEditrice casaEditrice){
        return repository.findByCasaEditrice(casaEditrice);
    }
    //DELETE
    public String delete(Long id){
        if(!repository.existsById(id)){
            throw new EntityNotFoundException("Libro not found");
        }
        repository.deleteById(id);
        return "Libro eliminato";
    }

}
