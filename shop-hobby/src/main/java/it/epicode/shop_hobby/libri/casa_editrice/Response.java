package it.epicode.shop_hobby.libri.casa_editrice;

import lombok.Data;

@Data
public class Response {
    private Long id;
    private String nome;
    private String indirizzo;
    private String citta;
    private String cap;
}
