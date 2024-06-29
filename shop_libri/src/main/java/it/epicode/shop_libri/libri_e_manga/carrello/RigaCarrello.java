package it.epicode.shop_libri.libri_e_manga.carrello;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import it.epicode.shop_libri.libri_e_manga.cartacei.Cartaceo;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

@Data
@Entity
@Table(name = "righe_carrello")
public class RigaCarrello {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "carrello_id")
    @JsonIgnoreProperties("righeCarrello")
    @ToString.Exclude
    private Carrello carrello;

    @ManyToOne
    @JoinColumn(name = "cartaceo_id")
    @JsonIgnoreProperties({"righeCarrello"})
    @ToString.Exclude
    private Cartaceo cartaceo;


    private int quantita;
    private double prezzo;


}
