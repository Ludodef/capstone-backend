package it.epicode.shop_hobby.libri.libro;

import it.epicode.shop_hobby.libri.autore.Response;
import it.epicode.shop_hobby.libri.genere.Genere;
import lombok.Data;

import java.util.List;
@Data
public class CompleteResponse {
    private Long id;
    private String titolo;
    private Response autore;
    private List<Genere> genere;
    private it.epicode.shop_hobby.libri.casa_editrice.Response casaEditrice;
    private it.epicode.shop_hobby.libri.saghe.Response saga;



}
