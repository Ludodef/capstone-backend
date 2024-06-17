package it.epicode.shop_hobby.manga.saghe_manga;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SagaMangaService {
    @Autowired
    private SagaMangaRepository sagaMangaRepository;

    public List<SagaManga> findAll(){
        return sagaMangaRepository.findAll();
    }

}
