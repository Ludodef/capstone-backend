package it.epicode.shop_libri.libri_e_manga.cartacei;

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

    public List<Cartaceo> findAll() {
        return repository.findAll();
    }
    public Response findById(Long id) {
        if(!repository.existsById(id)){
            throw new EntityNotFoundException("Libro non trovato");
        }
        // Se l'entity esiste, viene recuperato e le sue proprietà vengono copiate in un oggetto CasaEditriceResponse.
        Cartaceo entity = repository.findById(id).get();
        Response response = new Response();
        BeanUtils.copyProperties(entity, response);
        return response;
    }

    @Transactional
    public Response create(Request request){
        // Questo metodo crea un nuovo entity.
        // Le proprietà dell'entity vengono copiate da un oggetto CasaEditriceRequest.
        Cartaceo entity = new Cartaceo();
        BeanUtils.copyProperties(request, entity);
        // L'entity viene quindi salvato nel database e le sue proprietà vengono copiate in un oggetto CasaEditriceResponse.
        Response response = new Response();
        BeanUtils.copyProperties(entity, response);
        repository.save(entity);
        return response;
    }

    public Response modify(Long id, Request request){
        // Questo metodo modifica un entity esistente.
        // Prima verifica se l'entity esiste nel database. Se non esiste, viene generata un'eccezione.
        if(!repository.existsById(id)){
            throw new EntityNotFoundException("Libro non trovato");
        }
        // Se l'entity esiste, le sue proprietà vengono modificate con quelle presenti nell'oggetto CasaEditriceRequest.
        Cartaceo entity = repository.findById(id).get();
        BeanUtils.copyProperties(request, entity);
        // L'entity modificato viene quindi salvato nel database e le sue proprietà vengono copiate in un oggetto CasaEditriceResponse.
        repository.save(entity);
        Response response = new Response();
        BeanUtils.copyProperties(entity, response);
        return response;
    }

    public String delete(Long id){
        // Questo metodo elimina un CasaEditrice dal database.
        // Prima verifica se l'CasaEditrice esiste nel database. Se non esiste, viene generata un'eccezione.
        if(!repository.existsById(id)){
            throw  new EntityNotFoundException("Libro non trovato");
        }
        // Se l'CasaEditrice esiste, viene eliminato dal database.
        repository.deleteById(id);
        return "Libro eliminato";
    }
}
