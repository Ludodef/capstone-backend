package it.epicode.shop_hobby.libri.saghe;

import it.epicode.shop_hobby.libri.autore.Autore;
import it.epicode.shop_hobby.libri.saghe.Request;
import it.epicode.shop_hobby.libri.saghe.Response;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SagaService {

    @Autowired
    private SagaRepository repository;

    public List<Saga> findAll(){
        return repository.findAll();
    }

    public Response findbyId(Long id){
        if(!repository.existsById(id)){
            throw new EntityNotFoundException("Saga non trovata");
        }
        Saga entity = repository.findById(id).get();
        Response response = new Response();
        BeanUtils.copyProperties(entity, response);
        return response;
    }

    public Response create(Request request){
        Saga entity = new Saga();
        BeanUtils.copyProperties(request,entity);
        Response response = new Response();
        BeanUtils.copyProperties(entity, response);
        repository.save(entity);
        return response;
    }

    public Response modify(Long id, Request request){
        if(!repository.existsById(id)){
            throw new EntityNotFoundException("Saga non trovata");
        }
        Saga entity = repository.findById(id).get();
        BeanUtils.copyProperties(request,entity);
        repository.save(entity);
        Response response = new Response();
        BeanUtils.copyProperties(entity, response);
        return response;
    }

    public String delete(Long id){
        if(!repository.existsById(id)){
            throw new EntityNotFoundException("Saga non trovata");
        }
        repository.deleteById(id);
        return "Saga eliminata con successo";
    }
}
