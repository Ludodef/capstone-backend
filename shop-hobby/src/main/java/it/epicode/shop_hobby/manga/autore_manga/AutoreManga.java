package it.epicode.shop_hobby.manga.autore_manga;

import it.epicode.shop_hobby.manga.manga.Manga;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Entity
@Data
@Table(name = "autori_manga")
public class AutoreManga {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(length = 50)
    private String nome;
    @Column(length = 50)
    private String cognome;

    @OneToMany(mappedBy = "autoreManga")
    @ToString.Exclude
    private List<Manga> manga;
}
