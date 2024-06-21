package it.epicode.shop_hobby.libri.libro;

import it.epicode.shop_hobby.libri.saghe.Saga;
import lombok.Data;

import java.util.List;

@Data

public class Request {
    private Long id;
    private String titolo;
    private List<Long> idGenere;
    private Long idAutore;
    private Long idCasaEditrice;
    private Long idSaga;
    private double prezzo;
    private String trama;
    private String image;



}

