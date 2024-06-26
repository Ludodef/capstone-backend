package it.epicode.shop_libri.libri_e_manga.genere;


import it.epicode.shop_libri.libri_e_manga.cartacei.Cartaceo;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Entity
@Data
@Table(name = "generi")
public class Genere {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String descrizione;

    @OneToMany(mappedBy = "genere")
    @ToString.Exclude
    private List<Cartaceo> cartacei;

}
