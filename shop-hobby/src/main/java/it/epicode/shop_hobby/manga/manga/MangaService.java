package it.epicode.shop_hobby.manga.manga;




import it.epicode.shop_hobby.libri.libro.Libro;
import it.epicode.shop_hobby.libri.libro.ModifyBookAndAuthorRequest;
import it.epicode.shop_hobby.manga.autore_manga.AutoreManga;
import it.epicode.shop_hobby.manga.autore_manga.AutoreMangaRepository;
import it.epicode.shop_hobby.manga.casa_editrice_manga.CasaEditriceManga;
import it.epicode.shop_hobby.manga.casa_editrice_manga.CasaEditriceMangaRepository;
import it.epicode.shop_hobby.manga.genere_manga.GenereManga;
import it.epicode.shop_hobby.manga.genere_manga.GenereMangaRepository;
import it.epicode.shop_hobby.manga.saghe_manga.SagaManga;
import it.epicode.shop_hobby.manga.saghe_manga.SagaMangaRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MangaService {


    private final MangaRepository repository;
    private final AutoreMangaRepository autoreMangaRepository;
    private final CasaEditriceMangaRepository casaEditriceMangaRepository;
    private final GenereMangaRepository genereMangaRepository;
    private final SagaMangaRepository sagaMangaRepository;


    public List<Manga> findAll(){
        return repository.findAll();
    }

    @Transactional
    public CompleteResponse findbyId(Long id){
        if(!repository.existsById(id)){
            throw new EntityNotFoundException("Manga non trovato");
        }
        Manga entity = repository.findById(id).get();
       CompleteResponse completeResponse = new CompleteResponse();
        BeanUtils.copyProperties(entity, completeResponse);
        completeResponse.setGenere(entity.getGenereManga());
        it.epicode.shop_hobby.manga.autore_manga.Response autoreResponse = new it.epicode.shop_hobby.manga.autore_manga.Response();
        BeanUtils.copyProperties(entity.getAutoreManga(), autoreResponse);
        it.epicode.shop_hobby.manga.casa_editrice_manga.Response casaEditriceResponse = new it.epicode.shop_hobby.manga.casa_editrice_manga.Response();
        BeanUtils.copyProperties(entity.getCasaEditriceManga(), casaEditriceResponse);
        it.epicode.shop_hobby.manga.saghe_manga.Response sagaResponse = new it.epicode.shop_hobby.manga.saghe_manga.Response();
        BeanUtils.copyProperties(entity.getSagaManga(), sagaResponse);
        completeResponse.setAutore(autoreResponse);
        completeResponse.setCasaEditrice(casaEditriceResponse);
        completeResponse.setSaga(sagaResponse);
        return completeResponse;
    }

    public Response create(Request request){
        if(!autoreMangaRepository.existsById(request.getIdAutore())){
            throw new EntityNotFoundException("Autore non trovato");
        }
        if(!sagaMangaRepository.existsById(request.getIdSaga())){
            throw new EntityNotFoundException("Saga non trovata");
        }
        if(!casaEditriceMangaRepository.existsById(request.getIdCasaEditrice())){
            throw new EntityNotFoundException("Casa editrice non trovata");
        }
        Manga entity = new Manga();
        BeanUtils.copyProperties(request,entity);
        AutoreManga autoreManga = autoreMangaRepository.findById(request.getIdAutore()).get();
        CasaEditriceManga casaEditriceManga = casaEditriceMangaRepository.findById(request.getIdCasaEditrice()).get();
        List<GenereManga> genereManga = genereMangaRepository.findAllById(request.getIdGenere());
        SagaManga sagaManga = sagaMangaRepository.findById(request.getIdSaga()).get();
        entity.setAutoreManga(autoreManga);
        entity.setCasaEditriceManga(casaEditriceManga);
        entity.setGenereManga(genereManga);
        entity.setSagaManga(sagaManga);
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
        AutoreManga autoreManga = autoreMangaRepository.findById(request.getIdAutore()).get();
        CasaEditriceManga casaEditriceManga = casaEditriceMangaRepository.findById(request.getIdCasaEditrice()).get();
        List<GenereManga> genereManga = genereMangaRepository.findAllById(request.getIdGenere());
        SagaManga sagaManga = sagaMangaRepository.findById(request.getIdSaga()).get();

        BeanUtils.copyProperties(request,entity);
        entity.setAutoreManga(autoreManga);
        entity.setCasaEditriceManga(casaEditriceManga);
        entity.setGenereManga(genereManga);
        entity.setSagaManga(sagaManga);
        repository.save(entity);
       Response response = new Response();
        BeanUtils.copyProperties(entity, response);
        return response;
    }

    //reucupero di una lista di libri per autore
    public List<Manga> findByAutoreManga(AutoreManga autoreManga) {
        return repository.findByAutoreManga(autoreManga);

    }
    //per casa editrice
    public List<Manga> findByCasaEditriceManga(CasaEditriceManga casaEditriceManga) {
        return repository.findByCasaEditriceManga(casaEditriceManga);

    }
    public List<Manga> findBySagaManga(SagaManga sagaManga) {
        return repository.findBySagaManga(sagaManga);
    }
    public Manga findByTitoloManga(String titoloManga){
        return repository.findByTitoloManga(titoloManga);
    }

    public String delete(Long id){
        if(!repository.existsById(id)){
            throw new EntityNotFoundException("Manga non trovato");
        }
        repository.deleteById(id);
        return "Manga eliminato con successo";
    }

    public Response creaLibriEautori(CreateMangaAndAuthorRequest request){
        AutoreManga autoreManga = new AutoreManga();
        BeanUtils.copyProperties(request, autoreManga);
        Manga entity = new Manga();
        if(!casaEditriceMangaRepository.existsById(request.getIdCasaEditrice())){
            throw new EntityNotFoundException("Casa editrice not found");
        }
        CasaEditriceManga casaEditriceManga = casaEditriceMangaRepository.findById(request.getIdCasaEditrice()).get();
        if(!sagaMangaRepository.existsById(request.getIdSaga())){
            throw new EntityNotFoundException("Saga not found");
        }
        SagaManga sagaManga = sagaMangaRepository.findById(request.getIdSaga()).get();
        BeanUtils.copyProperties(request, entity);
        entity.setGenereManga(genereMangaRepository.findAllById(request.getIdGenere()));
        entity.setAutoreManga(autoreManga);
        entity.setCasaEditriceManga(casaEditriceManga);
        entity.setSagaManga(sagaManga);
        repository.save(entity);
        Response response = new Response();
        BeanUtils.copyProperties(response, entity);
        return response;
    }
    public Response modifyMangaAndAuthor(Long id, ModifyMangaAndAuthorRequest request){
        if(!repository.existsById(id)){
            throw new EntityNotFoundException("Libro not found");

        }
        Manga entity = repository.findById(id).get();
        entity.setTitoloManga(request.getTitolo());
        entity.getAutoreManga().setNome(request.getNomeAutoreManga());
        repository.save(entity);
        Response response = new Response();
        BeanUtils.copyProperties(response, entity);
        return response;
    }
}
