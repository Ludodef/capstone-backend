package it.epicode.shop_hobby.gadget.gadgets;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import it.epicode.shop_hobby.commons.Prodotto;
import it.epicode.shop_hobby.gadget.categorie.Categoria;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Entity
@Table(name = "gadgets")
@Data
public class Gadget extends Prodotto {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;


    private String image;

    @ManyToMany
    @JsonIgnoreProperties("gadgets")
    @ToString.Exclude
    private List<Categoria> categorie;





}
