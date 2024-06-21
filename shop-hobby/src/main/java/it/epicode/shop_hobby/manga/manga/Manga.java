package it.epicode.shop_hobby.manga.manga;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import it.epicode.shop_hobby.manga.autore_manga.AutoreManga;
import it.epicode.shop_hobby.manga.casa_editrice_manga.CasaEditriceManga;
import it.epicode.shop_hobby.manga.genere_manga.GenereManga;
import it.epicode.shop_hobby.manga.saghe_manga.SagaManga;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Entity
@Table(name = "manga")
@Data
public class Manga {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String titoloManga;

    @ManyToOne
    @JsonIgnoreProperties({"manga", "id"})
    @ToString.Exclude
    private AutoreManga autoreManga;

    @ManyToOne
    @JsonIgnoreProperties({"manga", "id"})
    @ToString.Exclude
    private CasaEditriceManga casaEditriceManga;

    @ManyToOne
    @JsonIgnoreProperties({"manga", "id"})
    @ToString.Exclude
    private SagaManga sagaManga;

    @ManyToMany
    @JsonIgnoreProperties({"manga", "id"})
    @ToString.Exclude
    private List<GenereManga> genereManga;

    private double prezzoManga;
    private String tramaManga;
    private String imageManga;



}
