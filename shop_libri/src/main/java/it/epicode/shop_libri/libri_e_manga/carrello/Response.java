package it.epicode.shop_libri.libri_e_manga.carrello;

import it.epicode.shop_libri.libri_e_manga.cartacei.Cartaceo;
import it.epicode.shop_libri.libri_e_manga.security.User;
import lombok.Data;

import java.util.List;

@Data
public class Response {
    private Long id;
    private User user;
    private List<Cartaceo> cartacei;
}
