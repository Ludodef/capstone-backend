package it.epicode.shop_libri.libri_e_manga.cartacei;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Request {

    private String titolo;
    private String generi;
    private String casaEditrice;
    private String saga;
    private TipoCartaceo tipoCartaceo;

    private double prezzo;
    private String trama;
    private String immagine;
    private String isbn;
    private int numeroPagine;

}
