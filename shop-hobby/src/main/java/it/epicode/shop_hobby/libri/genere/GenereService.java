package it.epicode.shop_hobby.libri.genere;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenereService {
    @Autowired
    private GenereRepository genereRepository;

    public List<Genere> findAll(){
        return genereRepository.findAll();
    }
}
