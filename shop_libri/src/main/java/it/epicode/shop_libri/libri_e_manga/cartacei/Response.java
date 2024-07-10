package it.epicode.shop_libri.libri_e_manga.cartacei;

import it.epicode.shop_libri.libri_e_manga.security.User;
import lombok.Data;

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
    private String immagine;
    private String isbn;
    private int numeroPagine;
    private boolean disponibile;
    private int quantita;
    private Long idUser;
    private User user;



}
