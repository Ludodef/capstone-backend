package it.epicode.shop_hobby.gadget.gadgets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GadgetService {
    @Autowired
    private GadgetRepository gadgetRepository;

    public List<Gadget> findAll(){
        return gadgetRepository.findAll();
    }
}
