package it.epicode.shop_hobby.manga.manga;

import lombok.Data;

import java.util.List;

@Data
public class Request {

    private Long id;
    private String titolo;
    private Long idAutore;
    private Long idCasaEditrice;
    private Long idSaga;
    private List<Long> idGenere;
    private double prezzo;
    private String trama;
    private String image;


}
