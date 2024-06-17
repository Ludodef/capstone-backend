package it.epicode.shop_hobby.gadget.categorie;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "categorie")
@Data
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(length = 50,unique = true)
    private String descrizione;
}
