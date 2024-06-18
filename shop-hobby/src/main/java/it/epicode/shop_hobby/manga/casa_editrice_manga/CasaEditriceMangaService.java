package it.epicode.shop_hobby.manga.casa_editrice_manga;

import it.epicode.shop_hobby.manga.autore_manga.AutoreManga;
import it.epicode.shop_hobby.manga.casa_editrice_manga.Request;
import it.epicode.shop_hobby.manga.casa_editrice_manga.Response;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CasaEditriceMangaService {
    @Autowired
    private CasaEditriceMangaRepository repository;

    public List<CasaEditriceManga> findAll(){
        return repository.findAll();
    }
    public Response findbyId(Long id){
        if(!repository.existsById(id)){
            throw new EntityNotFoundException("Casa editrice non trovato");
        }
        CasaEditriceManga entity = repository.findById(id).get();
        Response response = new Response();
        BeanUtils.copyProperties(entity, response);
        repository.save(entity);
        return response;
    }

    public Response create(Request request){
        CasaEditriceManga entity = new CasaEditriceManga();
        BeanUtils.copyProperties(request,entity);
        Response response = new Response();
        BeanUtils.copyProperties(entity, response);
        return response;
    }
    public Response modify(Long id, Request request){
        if(!repository.existsById(id)){
            throw new EntityNotFoundException("Casa editrice non trovato");
        }
        CasaEditriceManga entity = repository.findById(id).get();
        BeanUtils.copyProperties(request,entity);
        repository.save(entity);
        Response response = new Response();
        BeanUtils.copyProperties(entity, response);
        return response;
    }

    public String delete(Long id){
        if(!repository.existsById(id)){
            throw new EntityNotFoundException("Casa editrice non trovato");
        }
        repository.deleteById(id);
        return "Casa editrice eliminato con successo";
    }
}
