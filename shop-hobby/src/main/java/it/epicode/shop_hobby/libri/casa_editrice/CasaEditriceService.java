package it.epicode.shop_hobby.libri.casa_editrice;


import it.epicode.shop_hobby.libri.casa_editrice.Request;
import it.epicode.shop_hobby.libri.casa_editrice.Response;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CasaEditriceService {
    @Autowired
    private CasaEditriceRepository repository;

    public List<CasaEditrice> findAll(){
        return repository.findAll();
    }
    public Response findbyId(Long id){
        if(!repository.existsById(id)){
            throw new EntityNotFoundException("Casa editrice non trovata");
        }
        CasaEditrice entity = repository.findById(id).get();
        Response response = new Response();
        BeanUtils.copyProperties(entity, response);
        return response;
    }

    public Response create(Request request){
        CasaEditrice entity = new CasaEditrice();
        BeanUtils.copyProperties(request,entity);
        Response response = new Response();
        BeanUtils.copyProperties(entity, response);
        repository.save(entity);
        return response;
    }

    public Response modify(Long id, Request request){
        if(!repository.existsById(id)){
            throw new EntityNotFoundException("Casa editrice non trovata");
        }
        CasaEditrice entity = repository.findById(id).get();
        BeanUtils.copyProperties(request,entity);
        repository.save(entity);
        Response response = new Response();
        BeanUtils.copyProperties(entity, response);
        return response;
    }

    public String delete(Long id){
        if(!repository.existsById(id)){
            throw new EntityNotFoundException("Casa editrice non trovata");
        }
        repository.deleteById(id);
        return "Autore eliminato con successo";
    }
}
