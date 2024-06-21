package it.epicode.shop_hobby.gadget.gadgets;


import it.epicode.shop_hobby.gadget.categorie.Categoria;
import it.epicode.shop_hobby.gadget.categorie.CategoriaRepository;
import it.epicode.shop_hobby.libri.libro.Libro;
import it.epicode.shop_hobby.libri.libro.LibroRepository;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GadgetService {

    @Autowired
    private GadgetRepository repository;
    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<Gadget> findAll(){
        return repository.findAll();
    }

    public Response findbyId(Long id){
        if(!repository.existsById(id)){
            throw new EntityNotFoundException("Saga non trovata");
        }
        Gadget entity = repository.findById(id).get();
        Response response = new Response();
        BeanUtils.copyProperties(entity, response);
        return response;
    }

    public Response create(Request request){

        Gadget entity = new Gadget();
        BeanUtils.copyProperties(request,entity);
        List<Categoria> categorie = categoriaRepository.findAllById(request.getIdCategoria());
        entity.setCategoria(categorie);
        Response response = new Response();
        BeanUtils.copyProperties(entity, response);
        repository.save(entity);
        return response;
    }

    public Response modify(Long id, Request request){
        if(!repository.existsById(id)){
            throw new EntityNotFoundException("Gadget non trovato");
        }
        Gadget entity = repository.findById(id).get();

        List<Categoria> categorie = categoriaRepository.findAllById(request.getIdCategoria());


        BeanUtils.copyProperties(request,entity);
        entity.setCategoria(categorie);
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
