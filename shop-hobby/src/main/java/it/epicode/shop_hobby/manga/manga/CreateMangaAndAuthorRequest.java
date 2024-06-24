package it.epicode.shop_hobby.manga.manga;

import lombok.Data;

import java.util.List;

@Data
public class CreateMangaAndAuthorRequest {
    private String titolo;
    private Long idCasaEditrice;
    private List<Long> idGenere;
    private Long idSaga;
    private String nome;
    private String cognome;

}

