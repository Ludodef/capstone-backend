package it.epicode.shop_libri.libri_e_manga.saga;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import it.epicode.shop_libri.libri_e_manga.cartacei.Cartaceo;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Entity
@Data
@Table(name= "saghe")
public class Saga {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String nome;
    private int numeroVolumi;

    @OneToMany(mappedBy = "saga")
    @ToString.Exclude
    @JsonIgnoreProperties("saga")
    private List<Cartaceo> cartacei;
}
