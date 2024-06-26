package it.epicode.shop_libri.libri_e_manga.autori;

import it.epicode.shop_libri.libri_e_manga.cartacei.Cartaceo;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Entity
@Data
@Table(name = "autori")
public class Autore {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(length = 50)
    private String nome;
    @Column(length =50)
    private String cognome;

    @OneToMany(mappedBy = "autore")
    @ToString.Exclude
    private List<Cartaceo> cartacei;
}
