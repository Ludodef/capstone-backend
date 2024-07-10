package it.epicode.shop_libri.libri_e_manga.cartacei;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import it.epicode.shop_libri.libri_e_manga.carrello.RigaCarrello;
import it.epicode.shop_libri.libri_e_manga.security.User;
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

    private String autore;

    private String genere;

    private String casaEditrice;

    private String saga;



    private double prezzo;
    @Column(length = 1000)
    private String trama;
    private String immagine;
    private String isbn;
    private int numeroPagine;
    private boolean disponibile = true;
    private int quantita;



    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnoreProperties({"cartacei", "carrello"})
    @ToString.Exclude
    private User user;

    @OneToMany(mappedBy = "cartaceo")
    @JsonIgnoreProperties({"cartacei", "carrello"})
    private List<RigaCarrello> righeCarrello;

}
