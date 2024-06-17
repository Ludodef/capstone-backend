package it.epicode.shop_hobby.gadget.categorie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {

   @Autowired
   private CategoriaRepository categoriaRepository;

   public List<Categoria> findAll(){
       return categoriaRepository.findAll();
   }
}
