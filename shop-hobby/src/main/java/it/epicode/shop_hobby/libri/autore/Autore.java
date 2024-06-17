package it.epicode.shop_hobby.libri.autore;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "autori")
public class Autore {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(length = 50)
    private String nome;
    @Column(length = 50)
    private String cognome;

}
