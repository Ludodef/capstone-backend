package it.epicode.shop_hobby.manga.casa_editrice_manga;

import lombok.Data;

@Data
public class Response {
    private Long id;
    private String nome;
    private String indirizzo;
    private String citta;
    private String cap;
}
