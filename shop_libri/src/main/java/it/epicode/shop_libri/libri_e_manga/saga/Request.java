package it.epicode.shop_libri.libri_e_manga.saga;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Request {

    private String nome;
    private  int numeroVolumi;
}
