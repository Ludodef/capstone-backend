package it.epicode.shop_libri.libri_e_manga.carrello;

import it.epicode.shop_libri.libri_e_manga.cartacei.Cartaceo;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor

public class Request {
    private Long userId;
    private List<Cartaceo> cartacei;

}
