package it.epicode.shop_hobby.carrello;

import it.epicode.shop_hobby.commons.Prodotto;
import it.epicode.shop_hobby.security.User;
import lombok.Data;

import java.util.List;

@Data
public class CarrelloResponse {
    private Long id;
    private User user;
    private List<Prodotto> prodotti;
}
