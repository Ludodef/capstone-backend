package it.epicode.shop_hobby.libri.casa_editrice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CasaEditriceService {
    @Autowired
    private CasaEditriceRepository casaEditriceRepository;

    public List<CasaEditrice> findAll(){
        return casaEditriceRepository.findAll();
    }
}
