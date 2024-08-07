package it.epicode.shop_libri.libri_e_manga.cartacei;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Request {

    private String titolo;
    private String genere;
    private String autore;
    private String casaEditrice;
    private String saga;
    private double prezzo;
    private String trama;
    private String isbn;
    private int numeroPagine;
    private int quantita;

}
