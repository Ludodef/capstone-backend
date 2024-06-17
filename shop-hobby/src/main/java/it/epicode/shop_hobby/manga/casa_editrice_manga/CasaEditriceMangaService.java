package it.epicode.shop_hobby.manga.casa_editrice_manga;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CasaEditriceMangaService {
    @Autowired
    private CasaEditriceMangaRepository casaEditriceMangaRepository;

    public List<CasaEditriceManga> findAll(){
        return casaEditriceMangaRepository.findAll();
    }
}
