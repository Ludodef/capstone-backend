package it.epicode.shop_hobby.libri.libro;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import it.epicode.shop_hobby.commons.Prodotto;
import it.epicode.shop_hobby.libri.autore.Autore;
import it.epicode.shop_hobby.libri.casa_editrice.CasaEditrice;
import it.epicode.shop_hobby.libri.genere.Genere;
import it.epicode.shop_hobby.libri.saghe.Saga;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Entity
@Data
@Table(name = "libri")
public class Libro extends Prodotto {

    private String titolo;


    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JsonIgnoreProperties({"libri" , "id"})
    @ToString.Exclude
    private Autore autore;

    @ManyToOne
    @JsonIgnoreProperties("libri")
    @ToString.Exclude
    private CasaEditrice casaEditrice;

    @ManyToMany
    @JsonIgnoreProperties("libri")
    @ToString.Exclude
    private List<Genere> generi;

    @ManyToOne
    @JsonIgnoreProperties("libri")
    @ToString.Exclude
    private Saga saga;




    private String image;



}
