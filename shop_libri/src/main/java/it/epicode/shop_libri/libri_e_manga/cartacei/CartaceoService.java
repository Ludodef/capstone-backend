package it.epicode.shop_libri.libri_e_manga.cartacei;

import it.epicode.shop_libri.libri_e_manga.security.UserRespository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartaceoService {

    @Autowired
    private CartaceoRepository repository;
    @Autowired
    UserRespository userRepository;

    public List<Cartaceo> findAll() {
        return repository.findAll();
    }
    public Response findById(Long id) {
        if(!repository.existsById(id)){
            throw new EntityNotFoundException("Libro non trovato");
        }

        Cartaceo entity = repository.findById(id).get();
        Response response = new Response();
        BeanUtils.copyProperties(entity, response);
        return response;
    }

    @Transactional
    public Response create(Request request){

        Cartaceo entity = new Cartaceo();
        BeanUtils.copyProperties(request, entity);

        Response response = new Response();
        BeanUtils.copyProperties(entity, response);
        repository.save(entity);
        return response;
    }

    public Response modify(Long id, Request request){

        if(!repository.existsById(id)){
            throw new EntityNotFoundException("Libro non trovato");
        }

        Cartaceo entity = repository.findById(id).get();
        BeanUtils.copyProperties(request, entity);

        repository.save(entity);
        Response response = new Response();
        BeanUtils.copyProperties(entity, response);
        return response;
    }

    public String delete(Long id){

        if(!repository.existsById(id)){
            throw  new EntityNotFoundException("Libro non trovato");
        }

        repository.deleteById(id);
        return "Libro eliminato";
    }

    @Transactional
    public Response updateProductQuantity(Long id, int newQuantity) {
        Cartaceo cartaceo = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Libro non trovato con ID: " + id));
        cartaceo.setQuantita(newQuantity);
        cartaceo.setDisponibile(newQuantity > 0);
        Cartaceo updatedProdotto = repository.save(cartaceo);
        Response response = new Response();
        BeanUtils.copyProperties(updatedProdotto, response);
        response.setUser(updatedProdotto.getUser());
        return response;

    }

}
