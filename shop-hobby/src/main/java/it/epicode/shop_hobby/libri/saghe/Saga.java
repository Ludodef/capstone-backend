package it.epicode.shop_hobby.libri.saghe;

import it.epicode.shop_hobby.libri.libro.Libro;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Entity
@Data
@Table(name = "saghe")
public class Saga {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String nome;
    private int volumi;

    @OneToMany(mappedBy = "saga")
    @ToString.Exclude
    private List<Libro> libri;
}

