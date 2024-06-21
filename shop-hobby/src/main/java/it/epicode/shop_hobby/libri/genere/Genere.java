package it.epicode.shop_hobby.libri.genere;

import it.epicode.shop_hobby.libri.libro.Libro;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Entity
@Table(name = "generi")
@Data
public class Genere {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(length = 50,unique = true)
    private String descrizione;

    @ManyToMany(mappedBy = "generi")
    @ToString.Exclude
    private List<Libro> libro;
}
