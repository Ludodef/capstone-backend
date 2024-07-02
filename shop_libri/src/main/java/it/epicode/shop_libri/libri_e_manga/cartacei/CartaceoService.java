package it.epicode.shop_libri.libri_e_manga.cartacei;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import it.epicode.shop_libri.libri_e_manga.autori.Autore;
import it.epicode.shop_libri.libri_e_manga.autori.AutoreRepository;
import it.epicode.shop_libri.libri_e_manga.case_editrici.CasaEditrice;
import it.epicode.shop_libri.libri_e_manga.case_editrici.CasaEditriceRepository;
import it.epicode.shop_libri.libri_e_manga.genere.Genere;
import it.epicode.shop_libri.libri_e_manga.genere.GenereRepository;
import it.epicode.shop_libri.libri_e_manga.saga.Saga;
import it.epicode.shop_libri.libri_e_manga.saga.SagaRepository;
import it.epicode.shop_libri.libri_e_manga.security.FileSizeExceededException;
import it.epicode.shop_libri.libri_e_manga.security.User;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class CartaceoService {
    @Autowired
    private CartaceoRepository repository;
    @Autowired
    private GenereRepository genereRepository;
    @Autowired
    private CasaEditriceRepository casaEditriceRepository;
    @Autowired
    private AutoreRepository autoreRepository;
    @Autowired
    private SagaRepository sagaRepository;
    @Autowired
    private Cloudinary cloudinary; // gestisce cloudinary
    @Value("${spring.servlet.multipart.max-file-size}")
    private String maxFileSize;

    //POST
    @Transactional
    public Response create(Request request){
        if (!autoreRepository.existsById(request.getIdAutore())){
            throw new EntityNotFoundException("Autore not found");
        }
        if (!casaEditriceRepository.existsById(request.getIdCasaEditrice())){
            throw new EntityNotFoundException("Casa Editrice not found");
        }
        if (!sagaRepository.existsById(request.getIdSaga())){
            throw  new EntityNotFoundException("Saga not found");
        }
        Cartaceo entity = new Cartaceo();
        BeanUtils.copyProperties(request, entity);
        Autore autore = autoreRepository.findById(request.getIdAutore()).get();
        CasaEditrice casaEditrice = casaEditriceRepository.findById(request.getIdCasaEditrice()).get();
        List<Genere> categorie = genereRepository.findAllById(request.getIdGeneri());
        Saga saga = sagaRepository.findById(request.getIdSaga()).get();
        entity.setAutore(autore);
        entity.setCasaEditrice(casaEditrice);
        entity.setGenere(categorie);
        entity.setSaga(saga);
        Response response = new Response();
        repository.save(entity);
        BeanUtils.copyProperties(entity, response);
        return response;

    }

    //PUT
    public Response modify(Long id, Request request){
        if(!autoreRepository.existsById(request.getIdAutore())){
            throw new EntityNotFoundException("Autore not found");
        }
        if(!casaEditriceRepository.existsById(request.getIdCasaEditrice())){
            throw  new EntityNotFoundException("Casa editrice not found");
        }
        if(!sagaRepository.existsById(request.getIdSaga())){
            throw new EntityNotFoundException("Saga not found");
        }
        if(!repository.existsById(id)){
            throw new EntityNotFoundException("Libro not found");
        }
        Cartaceo entity = repository.findById(id).get();
        Autore autore = autoreRepository.findById(request.getIdAutore()).get();
        CasaEditrice casaEditrice = casaEditriceRepository.findById(request.getIdCasaEditrice()).get();
        List<Genere> categorie = genereRepository.findAllById(request.getIdGeneri());
        Saga saga = sagaRepository.findById(request.getIdSaga()).get();
        BeanUtils.copyProperties(request, entity);
        entity.setAutore(autore);
        entity.setCasaEditrice(casaEditrice);
        entity.setGenere(categorie);
        entity.setSaga(saga);
        repository.save(entity);
        Response response = new Response();
        BeanUtils.copyProperties(entity, response);
        return response;
    }

    public List<CartaceoResponsePrj> findAll(){
        return repository.findAllBy();
    }

    @Transactional
    public CompleteResponse findById(Long id){
        if(!repository.existsById(id)){
            throw new EntityNotFoundException("Libro not found");
        }
        Cartaceo entity = repository.findById(id).get();
        CompleteResponse completeResponse = new CompleteResponse();
        BeanUtils.copyProperties(entity, completeResponse);
        completeResponse.setGenere(entity.getGenere());
        it.epicode.shop_libri.libri_e_manga.autori.Response autoreResponse = new it.epicode.shop_libri.libri_e_manga.autori.Response();
        BeanUtils.copyProperties(entity.getAutore(), autoreResponse);
        it.epicode.shop_libri.libri_e_manga.case_editrici.Response casaEditriceResponse = new it.epicode.shop_libri.libri_e_manga.case_editrici.Response();
        BeanUtils.copyProperties(entity.getCasaEditrice(), casaEditriceResponse);
        it.epicode.shop_libri.libri_e_manga.saga.Response sagaResponse = new it.epicode.shop_libri.libri_e_manga.saga.Response();
        BeanUtils.copyProperties(entity.getSaga(), sagaResponse);
        completeResponse.setAutore(autoreResponse);
        completeResponse.setCasaEditrice(casaEditriceResponse);
        completeResponse.setSaga(sagaResponse);
        return completeResponse;

    }

    //GET
    public List<Cartaceo> findByAutore(Autore autore){
        return repository.findByAutore(autore);
    }

    public List<Cartaceo> findByCasaEditrice(CasaEditrice casaEditrice){
        return repository.findByCasaEditrice(casaEditrice);
    }
    //DELETE
    public String delete(Long id){
        if(!repository.existsById(id)){
            throw new EntityNotFoundException("Libro not found");
        }
        repository.deleteById(id);
        return "Libro eliminato";
    }
    @Transactional
    public String uploadImage(Long id, MultipartFile image) throws IOException {
        long maxFileSize = getMaxFileSizeInBytes();
        if (image.getSize() > maxFileSize) {
            throw new FileSizeExceededException("File size exceeds the maximum allowed size");
        }

        Optional<Cartaceo> optionalCartaceo = repository.findById(id);
        Cartaceo cartaceo = optionalCartaceo.orElseThrow(() -> new EntityNotFoundException("Cartaceo not found with id: " + id));

        String existingPublicId = cartaceo.getImmagine();
        if (existingPublicId != null && !existingPublicId.isEmpty()) {
            cloudinary.uploader().destroy(existingPublicId, ObjectUtils.emptyMap());
        }

        Map<String, Object> uploadResult = cloudinary.uploader().upload(image.getBytes(), ObjectUtils.emptyMap());
        String publicId = (String) uploadResult.get("public_id");
        String url = (String) uploadResult.get("url");

        cartaceo.setImmagine(publicId);
        repository.save(cartaceo);

        return url;
    }


    //DELETE delete cloudinary file

    @Transactional
    public String deleteImage(Long id) throws IOException {
        Optional<Cartaceo> optionalCartaceo = repository.findById(id);
        Cartaceo cartaceo = optionalCartaceo.orElseThrow(() -> new EntityNotFoundException("Cartaceo not found with id: " + id));

        String publicId = cartaceo.getImmagine();
        if (publicId != null && !publicId.isEmpty()) {
            cloudinary.uploader().destroy(publicId, ObjectUtils.emptyMap());
            cartaceo.setImmagine(null);
            repository.save(cartaceo);
            return "Image deleted successfully";
        } else {
            return "No image found for deletion";
        }
    }


    // PUT update cloudinary file
    @Transactional
    public String updateImage(Long id, MultipartFile updatedImage) throws IOException {
        deleteImage(id);
        return uploadImage(id, updatedImage);
    }

    public long getMaxFileSizeInBytes() {
        String[] parts = maxFileSize.split("(?i)(?<=[0-9])(?=[a-z])");
        long size = Long.parseLong(parts[0]);
        String unit = parts[1].toUpperCase();
        switch (unit) {
            case "KB":
                size *= 1024;
                break;
            case "MB":
                size *= 1024 * 1024;
                break;
            case "GB":
                size *= 1024 * 1024 * 1024;
                break;
        }
        return size;
    }

}
