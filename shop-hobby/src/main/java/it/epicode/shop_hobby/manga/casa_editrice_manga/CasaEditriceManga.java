package it.epicode.shop_hobby.manga.casa_editrice_manga;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "case_editrici_manga")
public class CasaEditriceManga {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(length = 100,unique = true)
    private String nome;
    private String indirizzo;
    private String citta;
    @Column(length = 5)
    private String cap;
}
