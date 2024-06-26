package it.epicode.shop_libri.libri_e_manga.saga;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Request {

    private String nome;
    private  int numeroVolumi;
}
