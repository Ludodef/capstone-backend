package it.epicode.shop_hobby.manga.manga;

import it.epicode.shop_hobby.libri.genere.Genere;
import it.epicode.shop_hobby.manga.genere_manga.GenereManga;

import java.util.List;

public class CompleteResponse {
    private Long id;
    private String titolo;
    private it.epicode.shop_hobby.manga.autore_manga.Response autore;
    private List<GenereManga> genere;
    private it.epicode.shop_hobby.manga.casa_editrice_manga.Response casaEditrice;

}
