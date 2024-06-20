package it.epicode.shop_hobby.libri.libro;

import it.epicode.shop_hobby.libri.genere.Genere;
import it.epicode.shop_hobby.libri.genere.Response;

import java.util.List;

public class CompleteResponse {
    private Long id;
    private String titolo;
    private it.epicode.shop_hobby.libri.autore.Response autore;
    private List<Genere> genere;
    private it.epicode.shop_hobby.libri.casa_editrice.Response casaEditrice;

}
