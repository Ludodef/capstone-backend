package it.epicode.shop_hobby.manga.saghe_manga;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "saga_manga")
public class SagaManga {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String nome;
    private int volumi;
}

