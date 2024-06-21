package it.epicode.shop_hobby.gadget.gadgets;

import it.epicode.shop_hobby.gadget.categorie.Categoria;
import it.epicode.shop_hobby.libri.genere.Genere;
import lombok.Data;

import java.util.List;
@Data
public class CompleteResponse {
    private Long id;
    private String nome;
    private List<Categoria> categorie;


}
