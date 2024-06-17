package it.epicode.shop_hobby.manga.autore_manga;

import jakarta.persistence.*;
import lombok.Data;

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
}
