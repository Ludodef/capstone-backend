package it.epicode.shop_libri.libri_e_manga.case_editrici;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Request {

    @NotEmpty(message = "Il nome non puo' essere vuoto")
    private String nome;

}
