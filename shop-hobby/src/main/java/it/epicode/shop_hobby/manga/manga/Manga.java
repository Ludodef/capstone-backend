package it.epicode.shop_hobby.manga.manga;

import it.epicode.shop_hobby.manga.autore_manga.AutoreManga;
import it.epicode.shop_hobby.manga.casa_editrice_manga.CasaEditriceManga;
import it.epicode.shop_hobby.manga.genere_manga.GenereManga;
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
    private SagaManga saga;
    @ManyToMany
    private List<GenereManga> genere;

    private double prezzo;
    private String trama;
    private String image;


    public void setAutore(AutoreManga autore) {
        this.autore = autore;
    }

    public AutoreManga getAutore() {
        return autore;
    }

    public void setCasaEditrice(CasaEditriceManga casaEditrice) {
        this.casaEditrice = casaEditrice;
    }

    public CasaEditriceManga getCasaEditrice() {
        return casaEditrice;
    }

    public void setGenere(List<GenereManga> genere) {
        this.genere = genere;
    }

    public List<GenereManga> getGenere() {
        return genere;
    }

    public void setSaga(SagaManga saga) {
        this.saga = saga;
    }

    public SagaManga getSaga() {
        return saga;
    }
}
