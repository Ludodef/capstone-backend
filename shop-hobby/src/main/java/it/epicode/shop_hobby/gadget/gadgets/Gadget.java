package it.epicode.shop_hobby.gadget.gadgets;

import it.epicode.shop_hobby.gadget.categorie.Categoria;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "gadgets")
@Data
public class Gadget {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String nome;
    private String descrizione;
    private double prezzo;
    @ManyToOne
    private Categoria categoria;


}
