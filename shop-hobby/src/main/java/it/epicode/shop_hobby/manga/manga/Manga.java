package it.epicode.shop_hobby.manga.manga;

import it.epicode.shop_hobby.manga.autore_manga.AutoreManga;
import it.epicode.shop_hobby.manga.casa_editrice_manga.CasaEditriceManga;
import it.epicode.shop_hobby.manga.saghe_manga.SagaManga;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "manga")
@Data
public class Manga {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String titolo;
    @ManyToOne
    private AutoreManga autore;
    @ManyToOne
    private CasaEditriceManga casaEditrice;
    @ManyToMany
    private List<SagaManga> saga;

    private double prezzo;
    private String trama;
    private String image;


}
