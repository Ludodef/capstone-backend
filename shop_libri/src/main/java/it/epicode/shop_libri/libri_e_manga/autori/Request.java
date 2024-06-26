package it.epicode.shop_libri.libri_e_manga.autori;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class Request {

    @NotEmpty(message = "Il nome non puo' essere vuoto")
    private String nome;

    @NotEmpty(message = "Il cognome non puo' essere vuoto")
    private String cognome;

}

