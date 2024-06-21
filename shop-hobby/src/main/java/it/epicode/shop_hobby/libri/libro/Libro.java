package it.epicode.shop_hobby.libri.libro;

import it.epicode.shop_hobby.libri.autore.Autore;
import it.epicode.shop_hobby.libri.casa_editrice.CasaEditrice;
import it.epicode.shop_hobby.libri.genere.Genere;
import it.epicode.shop_hobby.libri.saghe.Saga;
import jakarta.persistence.*;
import lombok.Data;

import java.awt.*;
import java.util.List;

@Entity
@Data
@Table(name = "libri")
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String titolo;

    @ManyToOne
    private Autore autore;
    @ManyToOne
    private CasaEditrice casaEditrice;
    @ManyToMany
    private List<Genere> generi;

    private Saga saga;
    private double prezzo;
    private String trama;
    private String image;


    public void setAutore(Autore autore) {
        this.autore = autore;
    }

    public void setCasaEditrice(CasaEditrice casaEditrice) {
        this.casaEditrice = casaEditrice;
    }

    public void setGenere(List<Genere> genere) {
        this.generi = genere;
    }

    public void setSaga(Saga saga) {
        this.saga = saga;
    }

    public Long getId() {
        return id;
    }

    public CasaEditrice getCasaEditrice() {
        return casaEditrice;
    }

    public Autore getAutore() {
        return autore;
    }

    public List<Genere> getGeneri() {
        return generi;
    }

    public Saga getSaga() {
        return saga;
    }
}
