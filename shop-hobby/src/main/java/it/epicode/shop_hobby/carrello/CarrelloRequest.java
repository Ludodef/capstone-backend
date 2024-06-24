package it.epicode.shop_hobby.carrello;

import it.epicode.shop_hobby.commons.Prodotto;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CarrelloRequest {
    private Long userId;
    private List<Prodotto> prodotti;

}
