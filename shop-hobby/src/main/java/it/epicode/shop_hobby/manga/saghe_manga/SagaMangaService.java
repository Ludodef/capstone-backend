package it.epicode.shop_hobby.manga.saghe_manga;


import it.epicode.shop_hobby.manga.saghe_manga.Request;
import it.epicode.shop_hobby.manga.saghe_manga.Response;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SagaMangaService {
    @Autowired
    private SagaMangaRepository repository;

    public List<SagaManga> findAll(){
        return repository.findAll();
    }

    public Response findbyId(Long id){
        if(!repository.existsById(id)){
            throw new EntityNotFoundException("Autore non trovato");
        }
        SagaManga entity = repository.findById(id).get();
        Response response = new Response();
        BeanUtils.copyProperties(entity, response);
        repository.save(entity);
        return response;
    }

    public Response create(Request request){
        SagaManga entity = new SagaManga();
        BeanUtils.copyProperties(request,entity);
        Response response = new Response();
        BeanUtils.copyProperties(entity, response);
        return response;
    }
    public Response modify(Long id, Request request){
        if(!repository.existsById(id)){
            throw new EntityNotFoundException("Autore non trovato");
        }
        SagaManga entity = repository.findById(id).get();
        BeanUtils.copyProperties(request,entity);
        repository.save(entity);
       Response response = new Response();
        BeanUtils.copyProperties(entity, response);
        return response;
    }

    public String delete(Long id){
        if(!repository.existsById(id)){
            throw new EntityNotFoundException("Autore non trovato");
        }
        repository.deleteById(id);
        return "Autore eliminato con successo";
    }

}
