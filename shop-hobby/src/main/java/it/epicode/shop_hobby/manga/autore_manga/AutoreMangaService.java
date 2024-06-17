package it.epicode.shop_hobby.manga.autore_manga;

import it.epicode.shop_hobby.libri.autore.Autore;
import it.epicode.shop_hobby.libri.autore.AutoreRequest;
import it.epicode.shop_hobby.libri.autore.AutoreResponse;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AutoreMangaService {
    @Autowired
    private AutoreMangaRepository autoreMangaRepository;

    public List<AutoreManga> findAll(){
        return autoreMangaRepository.findAll();


    }
    public AutoreMangaResponse findbyId(Long id){
        if(!autoreMangaRepository.existsById(id)){
            throw new EntityNotFoundException("Autore non trovato");
        }
        AutoreManga autoreManga = autoreMangaRepository.findById(id).get();
        AutoreMangaResponse autoreMangaResponse = new AutoreMangaResponse();
        BeanUtils.copyProperties(autoreManga, autoreMangaResponse);
        autoreMangaRepository.save(autoreManga);
        return autoreMangaResponse;
    }

    public AutoreMangaResponse create(AutoreMangaRequest autoreMangaRequest){
        AutoreManga autoreManga = new AutoreManga();
        BeanUtils.copyProperties(autoreMangaRequest,autoreManga);
        AutoreMangaResponse autoreMangaResponse = new AutoreMangaResponse();
        BeanUtils.copyProperties(autoreManga,autoreMangaResponse);
        return autoreMangaResponse;
    }
    public AutoreMangaResponse modify(Long id, AutoreMangaRequest autoreMangaRequest){
        if(!autoreMangaRepository.existsById(id)){
            throw new EntityNotFoundException("Autore non trovato");
        }
        AutoreManga autoreManga = autoreMangaRepository.findById(id).get();
        BeanUtils.copyProperties(autoreMangaRequest,autoreManga);
        autoreMangaRepository.save(autoreManga);
        AutoreMangaResponse autoreMangaResponse = new AutoreMangaResponse();
        BeanUtils.copyProperties(autoreManga,autoreMangaResponse);
        return autoreMangaResponse;
    }

    public String delete(Long id){
        if(!autoreMangaRepository.existsById(id)){
            throw new EntityNotFoundException("Autore non trovato");
        }
        autoreMangaRepository.deleteById(id);
        return "Autore eliminato con successo";
    }
}
