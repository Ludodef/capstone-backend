package it.epicode.shop_hobby.carrello;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import it.epicode.shop_hobby.commons.Prodotto;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

@Data
@Entity
@Table(name = "righe_carrello")
public class RigaCarrello {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "carrello_id")
    @JsonIgnoreProperties("righeCarrello")
    @ToString.Exclude
    private Carrello carrello;

    @ManyToOne
    @JoinColumn(name = "prodotto_id")
    @JsonIgnoreProperties({"righeCarrello"})
    @ToString.Exclude
    private Prodotto prodotto;


    private int quantita;
    private double prezzo;


}
