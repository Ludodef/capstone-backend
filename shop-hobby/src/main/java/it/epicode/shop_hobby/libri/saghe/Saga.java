package it.epicode.shop_hobby.libri.saghe;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "saghe")
public class Saga {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String nome;
    private int volumi;
}

