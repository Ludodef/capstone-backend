package it.epicode.shop_libri.libri_e_manga.cartacei;

import it.epicode.shop_libri.libri_e_manga.security.User;
import lombok.Data;

import java.util.List;

@Data
public class Response {
    private Long id;
    private String titolo;
    private String genere;
    private String autore;
    private String casaEditrice;
    private String saga;


    private double prezzo;
    private String trama;
    private List<String> immagine;
    private String isbn;
    private int numeroPagine;
    private int quantita;





}
