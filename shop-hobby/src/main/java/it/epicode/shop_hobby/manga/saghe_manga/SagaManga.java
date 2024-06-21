package it.epicode.shop_hobby.manga.saghe_manga;

import it.epicode.shop_hobby.manga.manga.Manga;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Entity
@Data
@Table(name = "saga_manga")
public class SagaManga {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String nome;
    private int volumi;

    @OneToMany(mappedBy = "sagaManga")
    @ToString.Exclude
    private List<Manga> manga;
}

