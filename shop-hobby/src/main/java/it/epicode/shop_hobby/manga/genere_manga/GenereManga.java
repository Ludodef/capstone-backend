package it.epicode.shop_hobby.manga.genere_manga;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "generi_manga")
@Data
public class GenereManga {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(length = 50,unique = true)
    private String descrizione;
}
