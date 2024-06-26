package it.epicode.shop_libri.libri_e_manga.cartacei;

import it.epicode.shop_libri.libri_e_manga.genere.Genere;
import lombok.Data;

import java.util.List;

@Data
public class CompleteResponse {

    private Long id;
    private String titolo;
    private it.epicode.shop_libri.libri_e_manga.autori.Response autore;
    private List<Genere> genere;
    private it.epicode.shop_libri.libri_e_manga.case_editrici.Response casaEditrice;
    private it.epicode.shop_libri.libri_e_manga.saga.Response saga;
}
