package it.epicode.shop_libri.libri_e_manga.saga;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data

public class Response {

    private Long id;
    private String nome;
    private  int numeroVolumi;
}
