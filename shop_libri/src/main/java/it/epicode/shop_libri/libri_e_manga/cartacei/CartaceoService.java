package it.epicode.shop_libri.libri_e_manga.cartacei;

import com.cloudinary.Cloudinary;
import it.epicode.shop_libri.libri_e_manga.security.UserRespository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CartaceoService {

    @Autowired
    private CartaceoRepository repository;
    @Autowired
    private UserRespository userRepository;
    @Autowired
    private Cloudinary cloudinary;

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
    public Response create(Request request, MultipartFile[] files ) throws IOException {

        List<String> urls = new ArrayList<>();

        for (MultipartFile file : files) {
            var uploadResult = cloudinary.uploader().upload(file.getBytes(),
                    com.cloudinary.utils.ObjectUtils.asMap("public_id", request.getTitolo() + "_avatar_" + UUID.randomUUID().toString()));
            String url = uploadResult.get("url").toString();
            urls.add(url);
        }


        Cartaceo entity = new Cartaceo();
        entity.setImmagine(urls);
        BeanUtils.copyProperties(request, entity);

        Response response = new Response();
        repository.save(entity);
        BeanUtils.copyProperties(entity, response);

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
    public CompleteResponse updateProductQuantity(Long id, int newQuantity) {
        Cartaceo cartaceo = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Libro non trovato con ID: " + id));
        cartaceo.setQuantita(newQuantity);
        cartaceo.setDisponibile(newQuantity > 0);
        Cartaceo updatedProdotto = repository.save(cartaceo);
        CompleteResponse completeResponse = new CompleteResponse();
        BeanUtils.copyProperties(updatedProdotto, completeResponse);
        completeResponse.setUser(updatedProdotto.getUser());
        return completeResponse;

    }

    public List<Cartaceo> findByTitolo(String titolo) {
        return repository.findByTitoloContainingIgnoreCase(titolo);
    }



}
