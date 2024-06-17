package it.epicode.shop_hobby.manga.manga;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MangaService {
    @Autowired
    private MangaRepository mangaRepository;

    public List<Manga> findAll(){
        return mangaRepository.findAll();
    }
}
