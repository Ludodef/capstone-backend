package it.epicode.shop_hobby.gadget.categorie;


import it.epicode.shop_hobby.gadget.categorie.Request;
import it.epicode.shop_hobby.gadget.categorie.Response;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {

   @Autowired
   private CategoriaRepository repository;

   public List<Categoria> findAll(){
       return repository.findAll();
   }

    public Response findbyId(Long id){
        if(!repository.existsById(id)){
            throw new EntityNotFoundException("Categforia non trovata");
        }
        Categoria entity = repository.findById(id).get();
        Response response = new Response();
        BeanUtils.copyProperties(entity, response);
        return response;
    }

    public Response create(Request request){
        Categoria entity = new Categoria();
        BeanUtils.copyProperties(request,entity);
        Response response = new Response();
        BeanUtils.copyProperties(entity, response);
        repository.save(entity);
        return response;
    }

    public Response modify(Long id, Request request){
        if(!repository.existsById(id)){
            throw new EntityNotFoundException("Categoria non trovato");
        }
        Categoria entity = repository.findById(id).get();
        BeanUtils.copyProperties(request,entity);
        repository.save(entity);
        Response response = new Response();
        BeanUtils.copyProperties(entity, response);
        return response;
    }

    public String delete(Long id){
        if(!repository.existsById(id)){
            throw new EntityNotFoundException("Categoria non trovato");
        }
        repository.deleteById(id);
        return "Categoria eliminato con successo";
    }
}
