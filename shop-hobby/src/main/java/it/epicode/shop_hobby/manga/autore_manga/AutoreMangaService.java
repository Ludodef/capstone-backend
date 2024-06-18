package it.epicode.shop_hobby.manga.autore_manga;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AutoreMangaService {
    @Autowired
    private AutoreMangaRepository repository;

    public List<AutoreManga> findAll(){
        return repository.findAll();


    }
    public Response findbyId(Long id){
        if(!repository.existsById(id)){
            throw new EntityNotFoundException("Autore non trovato");
        }
        AutoreManga entity = repository.findById(id).get();
        Response response = new Response();
        BeanUtils.copyProperties(entity, response);
        repository.save(entity);
        return response;
    }

    public Response create(Request request){
        AutoreManga entity = new AutoreManga();
        BeanUtils.copyProperties(request,entity);
        Response response = new Response();
        BeanUtils.copyProperties(entity, response);
        return response;
    }
    public Response modify(Long id, Request request){
        if(!repository.existsById(id)){
            throw new EntityNotFoundException("Autore non trovato");
        }
        AutoreManga entity = repository.findById(id).get();
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
