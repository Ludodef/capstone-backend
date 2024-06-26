package it.epicode.shop_libri.libri_e_manga.case_editrici;

import it.epicode.shop_libri.libri_e_manga.cartacei.Cartaceo;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Entity
@Data
@Table(name = "case_editrici")
public class CasaEditrice {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(length = 100,unique = true)
    private String nome;

    @OneToMany(mappedBy = "casaEditrice")
    @ToString.Exclude
    private List<Cartaceo> cartacei;
}
