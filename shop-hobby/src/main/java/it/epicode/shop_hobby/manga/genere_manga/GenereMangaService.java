package it.epicode.shop_hobby.manga.genere_manga;

import it.epicode.shop_hobby.libri.genere.GenereRepository;

import it.epicode.shop_hobby.manga.genere_manga.Request;
import it.epicode.shop_hobby.manga.genere_manga.Response;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenereMangaService {
    @Autowired
    private GenereMangaRepository repository;

    public List<GenereManga> findAll(){
        return repository.findAll();
    }

    public Response findbyId(Long id){
        if(!repository.existsById(id)){
            throw new EntityNotFoundException("Autore non trovato");
        }
        GenereManga entity = repository.findById(id).get();
        Response response = new Response();
        BeanUtils.copyProperties(entity, response);
        repository.save(entity);
        return response;
    }

    public Response create(Request request){
        GenereManga entity = new GenereManga();
        BeanUtils.copyProperties(request,entity);
       Response response =new Response();
        BeanUtils.copyProperties(entity, response);
        return response;
    }
    public Response modify(Long id, Request request){
        if(!repository.existsById(id)){
            throw new EntityNotFoundException("Autore non trovato");
        }
        GenereManga entity = repository.findById(id).get();
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
