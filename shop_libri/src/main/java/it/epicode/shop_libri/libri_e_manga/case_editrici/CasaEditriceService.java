package it.epicode.shop_libri.libri_e_manga.case_editrici;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CasaEditriceService {

    @Autowired
    CasaEditriceRepository repository;

    // GET ALL
    public List<CasaEditrice> findAll(){
        return repository.findAll();
    }

    // GET per ID
    public Response findById(Long id){
        if(!repository.existsById(id)){
            throw new EntityNotFoundException("Casa Editrice non trovata");
        }

        CasaEditrice entity = repository.findById(id).get();
        Response response = new Response();
        BeanUtils.copyProperties(entity, response);
        return response;
    }

    // POST
    @Transactional
    public Response create(@Valid Request request){

        CasaEditrice entity = new CasaEditrice();
        BeanUtils.copyProperties(request, entity);
        repository.save(entity);
        Response response = new Response();
        BeanUtils.copyProperties(entity, response);
        repository.save(entity);
        return response;
    }

    // PUT
    public Response modify(Long id, Request request){
        if(!repository.existsById(id)){
            throw new EntityNotFoundException("Casa Editrice non trovata");
        }

        CasaEditrice entity = repository.findById(id).get();
        BeanUtils.copyProperties(request, entity);
        repository.save(entity);
        Response response = new Response();
        BeanUtils.copyProperties(entity, response);
        return response;
    }


    //DELETE
    public String delete(Long id){
        if(!repository.existsById(id)){
            throw  new EntityNotFoundException("Casa Editrice non trovata");
        }
        repository.deleteById(id);
        return "Casa Editrice eliminata";
    }
}
