package it.epicode.shop_hobby.gadget.gadgets;


import it.epicode.shop_hobby.gadget.categorie.Categoria;
import it.epicode.shop_hobby.gadget.categorie.CategoriaRepository;

import it.epicode.shop_hobby.manga.manga.Manga;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GadgetService {

    private final  GadgetRepository repository;
    private final CategoriaRepository categoriaRepository;

    public List<Gadget> findAll(){
        return repository.findAll();
    }

    public CompleteResponse findbyId(Long id){
        if(!repository.existsById(id)){
            throw new EntityNotFoundException("Saga non trovata");
        }
        Gadget entity = repository.findById(id).get();
        CompleteResponse completeResponse = new CompleteResponse();
        BeanUtils.copyProperties(entity, completeResponse);
        completeResponse.setCategorie(entity.getCategorie());
        return completeResponse;
    }

    public Response create(Request request){

        Gadget entity = new Gadget();
        BeanUtils.copyProperties(request,entity);
        List<Categoria> categorie = categoriaRepository.findAllById(request.getIdCategoria());
        entity.setCategorie(categorie);
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
        entity.setCategorie(categorie);
        repository.save(entity);
        Response response = new Response();
        BeanUtils.copyProperties(entity, response);
        return response;
    }

    public Gadget findByNome(String nome){
        return repository.findByNome(nome);
    }

    public String delete(Long id){
        if(!repository.existsById(id)){
            throw new EntityNotFoundException("Gadget non trovato");
        }
        repository.deleteById(id);
        return "Gadget eliminato con successo";
    }
}
