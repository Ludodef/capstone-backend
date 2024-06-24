package it.epicode.shop_hobby.commons;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import it.epicode.shop_hobby.carrello.RigaCarrello;
import it.epicode.shop_hobby.security.User;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@Entity
@Table(name = "prodotti")
public class Prodotto {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(nullable = false)
    private String nome;
    @Column(length = 150)
    private String descrizione;
    @Column(nullable = false)
    private Double prezzo;
    @Column(nullable = false)
    private int quantita;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnoreProperties({"prodotti", "carrello"})
    @ToString.Exclude
    private User user;

    @OneToMany(mappedBy = "prodotto")
    @JsonIgnoreProperties({"prodotto", "carrello"})
    private List<RigaCarrello> righeCarrello;



    private boolean isAvailable = true;

}
