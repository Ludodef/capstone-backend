package it.epicode.shop_libri.libri_e_manga.autori;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@Validated
public class AutoreService {

    @Autowired
    private AutoreRepository repository;

    // GET ALL
    public List<AutoreResponsePrj> findAll(){

        return repository.findAllBy();
    }

    // GET per ID
    public Response findById(Long id){
        if(!repository.existsById(id)){
            throw new EntityNotFoundException("Autore non trovato");
        }
        Autore entity = repository.findById(id).get();
        Response response = new Response();
        BeanUtils.copyProperties(entity, response);
        return response;
    }

    // POST
    @Transactional
    public Response create(@Valid Request request){
        Autore entity = new Autore();
        BeanUtils.copyProperties(request, entity);

        Response response = new Response();
        BeanUtils.copyProperties(entity, response);
        repository.save(entity);
        return response;
    }

    // PUT
    public Response modify(Long id, Request request){
        if(!repository.existsById(id)){
            throw new EntityNotFoundException("Autore non trovato");
        }
        Autore entity = repository.findById(id).get();
        BeanUtils.copyProperties(request, entity);
        repository.save(entity);
        Response response = new Response();
        BeanUtils.copyProperties(entity, response);
        return response;
    }

    //DELETE
    public String delete(Long id){

        if(!repository.existsById(id)){
            throw  new EntityNotFoundException("Autore non trovato");
        }
        repository.deleteById(id);
        return "Autore eliminato";
    }
}
