package it.epicode.shop_hobby.manga.genere_manga;

import it.epicode.shop_hobby.libri.genere.GenereRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenereMangaService {
    @Autowired
    private GenereMangaRepository genereMangaRepository;

    public List<GenereManga> findAll(){
        return genereMangaRepository.findAll();
    }

}
