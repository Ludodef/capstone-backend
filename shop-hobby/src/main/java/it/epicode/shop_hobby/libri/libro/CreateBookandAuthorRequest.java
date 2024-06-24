package it.epicode.shop_hobby.libri.libro;

import lombok.Data;

import java.util.List;
@Data
public class CreateBookandAuthorRequest {
    private String titolo;
    private Long idCasaEditrice;
    private List<Long> idGenere;
    private Long idSaga;
    private String nome;
    private String cognome;

}

