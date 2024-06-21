package it.epicode.shop_hobby.manga.genere_manga;

import it.epicode.shop_hobby.manga.manga.Manga;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Entity
@Table(name = "generi_manga")
@Data
public class GenereManga {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(length = 50,unique = true)
    private String descrizione;

    @ManyToMany
    @ToString.Exclude
    private List<Manga> manga;
}
