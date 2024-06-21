package it.epicode.shop_hobby.gadget.categorie;

import it.epicode.shop_hobby.gadget.gadgets.Gadget;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Entity
@Table(name = "categorie")
@Data
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(length = 50,unique = true)
    private String descrizione;

    @ManyToMany(mappedBy = "categorie")
    @ToString.Exclude
    private List<Gadget> gadgets;
}
