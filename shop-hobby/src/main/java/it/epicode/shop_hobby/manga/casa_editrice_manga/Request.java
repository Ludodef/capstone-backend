package it.epicode.shop_hobby.manga.casa_editrice_manga;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class Request {
    private String nome;
    private String indirizzo;
    private String citta;
    private String cap;
}
