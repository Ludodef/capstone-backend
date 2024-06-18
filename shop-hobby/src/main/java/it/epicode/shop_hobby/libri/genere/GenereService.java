package it.epicode.shop_hobby.libri.genere;


import it.epicode.shop_hobby.libri.genere.Request;
import it.epicode.shop_hobby.libri.genere.Response;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenereService {
    @Autowired
    private GenereRepository repository;

    public List<Genere> findAll(){
        return repository.findAll();
    }
    public Response findbyId(Long id){
        if(!repository.existsById(id)){
            throw new EntityNotFoundException("Genere non trovato");
        }
        Genere entity = repository.findById(id).get();
        Response response = new Response();
        BeanUtils.copyProperties(entity, response);
        return response;
    }

    public Response create(Request request){
        Genere entity = new Genere();
        BeanUtils.copyProperties(request,entity);
        Response response = new Response();
        BeanUtils.copyProperties(entity, response);
        repository.save(entity);
        return response;
    }

    public Response modify(Long id, Request request){
        if(!repository.existsById(id)){
            throw new EntityNotFoundException("Genere non trovato");
        }
        Genere entity = repository.findById(id).get();
        BeanUtils.copyProperties(request,entity);
        repository.save(entity);
        Response response = new Response();
        BeanUtils.copyProperties(entity, response);
        return response;
    }

    public String delete(Long id){
        if(!repository.existsById(id)){
            throw new EntityNotFoundException("Genere non trovato");
        }
        repository.deleteById(id);
        return "Genere eliminato con successo";
    }
}
