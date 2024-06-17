package it.epicode.shop_hobby.libri.saghe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SagaService {

    @Autowired
    private SagaRepository sagaRepository;

    public List<Saga> findAll(){
        return sagaRepository.findAll();
    }
}
