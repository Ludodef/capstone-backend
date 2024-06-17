package it.epicode.shop_hobby.libri.genere;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "generi")
@Data
public class Genere {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(length = 50,unique = true)
    private String descrizione;
}
