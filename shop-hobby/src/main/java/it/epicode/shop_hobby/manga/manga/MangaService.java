package it.epicode.shop_hobby.manga.manga;



import it.epicode.shop_hobby.libri.saghe.Saga;
import it.epicode.shop_hobby.manga.autore_manga.AutoreManga;
import it.epicode.shop_hobby.manga.autore_manga.AutoreMangaRepository;
import it.epicode.shop_hobby.manga.casa_editrice_manga.CasaEditriceManga;
import it.epicode.shop_hobby.manga.casa_editrice_manga.CasaEditriceMangaRepository;
import it.epicode.shop_hobby.manga.genere_manga.GenereManga;
import it.epicode.shop_hobby.manga.genere_manga.GenereMangaRepository;
import it.epicode.shop_hobby.manga.saghe_manga.SagaManga;
import it.epicode.shop_hobby.manga.saghe_manga.SagaMangaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MangaService {

    @Autowired
    private MangaRepository repository;
    @Autowired
    private AutoreMangaRepository autoreMangaRepository;
    @Autowired
    private CasaEditriceMangaRepository casaEditriceMangaRepository;
    @Autowired
    private GenereMangaRepository genereMangaRepository;
    @Autowired
    private SagaMangaRepository sagaMangaRepository;


    public List<Manga> findAll(){
        return repository.findAll();
    }

    public Response findbyId(Long id){
        if(!repository.existsById(id)){
            throw new EntityNotFoundException("Saga non trovata");
        }
        Manga entity = repository.findById(id).get();
       Response response = new Response();
        BeanUtils.copyProperties(entity, response);
        return response;
    }

    public Response create(Request request){
        if(!autoreMangaRepository.existsById(request.getIdAutore())){
            throw new EntityNotFoundException("Autore non trovato");
        }
        if(!casaEditriceMangaRepository.existsById(request.getIdCasaEditrice())){
            throw new EntityNotFoundException("Casa editrice non trovata");
        }
        Manga entity = new Manga();
        BeanUtils.copyProperties(request,entity);
        AutoreManga autore = autoreMangaRepository.findById(request.getIdAutore()).get();
        CasaEditriceManga casaEditrice = casaEditriceMangaRepository.findById(request.getIdCasaEditrice()).get();
        List<GenereManga> genere = genereMangaRepository.findAllById(request.getIdGenere());
        SagaManga saga = sagaMangaRepository.findById(request.getIdSaga()).get();
        entity.setAutore(autore);
        entity.setCasaEditrice(casaEditrice);
        entity.setGenere(genere);
        entity.setSaga(saga);
        Response response = new Response();
        BeanUtils.copyProperties(entity, response);
        repository.save(entity);
        return response;
    }

    public Response modify(Long id, Request request){
        if(!autoreMangaRepository.existsById(request.getIdAutore())){
            throw new EntityNotFoundException("Autore non trovato");
        }
        if(!casaEditriceMangaRepository.existsById(request.getIdCasaEditrice())){
            throw new EntityNotFoundException("Casa editrice non trovata");
        }
        if(!repository.existsById(id)){
            throw new EntityNotFoundException("Manga non trovato");
        }
        Manga entity = repository.findById(id).get();
        AutoreManga autore = autoreMangaRepository.findById(request.getIdAutore()).get();
        CasaEditriceManga casaEditrice = casaEditriceMangaRepository.findById(request.getIdCasaEditrice()).get();
        List<GenereManga> genere = genereMangaRepository.findAllById(request.getIdGenere());
        SagaManga saga = sagaMangaRepository.findById(request.getIdSaga()).get();

        BeanUtils.copyProperties(request,entity);
        entity.setAutore(autore);
        entity.setCasaEditrice(casaEditrice);
        entity.setGenere(genere);
        entity.setSaga(saga);
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
