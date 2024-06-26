package it.epicode.shop_libri.libri_e_manga.autori;

import lombok.Data;

@Data
public class CompleteRequest {
    private Long id;
    private String nome;
    private String cognome;

}

