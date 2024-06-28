package it.epicode.shop_libri.libri_e_manga.cartacei;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import it.epicode.shop_libri.libri_e_manga.autori.Autore;
import it.epicode.shop_libri.libri_e_manga.case_editrici.CasaEditrice;
import it.epicode.shop_libri.libri_e_manga.genere.Genere;
import it.epicode.shop_libri.libri_e_manga.saga.Saga;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Entity
@Data
@Table(name = "cartacei")
public class Cartaceo {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String titolo;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @ToString.Exclude
    @JsonIgnoreProperties({"cartacei", "id"})
    private Autore autore;
    @ManyToMany
    @ToString.Exclude
    @JsonIgnoreProperties({"cartacei", "id"})
    private List<Genere> genere;
    @ManyToOne
    @ToString.Exclude
    @JsonIgnoreProperties({"cartacei", "id"})
    private CasaEditrice casaEditrice;
    @ManyToOne
    @ToString.Exclude
    @JsonIgnoreProperties({"cartacei", "id"})
    private Saga saga;

    private TipoCartaceo tipoCartaceo;

    private double prezzo;
    @Column(length = 1000)
    private String trama;
    private String immagine;
    private String isbn;
    private int numeroPagine;
    private boolean disponibile = true;
}
