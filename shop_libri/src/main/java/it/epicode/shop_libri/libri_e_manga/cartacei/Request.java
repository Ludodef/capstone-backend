package it.epicode.shop_libri.libri_e_manga.cartacei;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Request {

    private String titolo;
    private List<Long> idGeneri;
    private Long idAutore;
    private Long idCasaEditrice;
    private Long idSaga;
    private TipoCartaceo tipoCartaceo;

    private double prezzo;
    private String trama;
    private String immagine;
    private String isbn;
    private int numeroPagine;

}
