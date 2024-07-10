package it.epicode.shop_libri.libri_e_manga.security;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import it.epicode.shop_libri.libri_e_manga.carrello.Carrello;
import it.epicode.shop_libri.libri_e_manga.cartacei.Cartaceo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(setterPrefix = "with")
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, nullable = false)
    private String firstName;
    @Column(length = 50, nullable = false)
    private String lastName;
    private String username;
    private String email;
    @Column(length = 125, nullable = false)
    private String password;
    private String avatar;
    @ManyToMany(fetch = FetchType.EAGER)
    private final List<Roles> roles = new ArrayList<>();


    @OneToMany(mappedBy = "user")
    @JsonIgnoreProperties({"user","righeCarrello"})
    private List<Cartaceo> cartacei;
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties({"user","righeCarrello"})
    private Carrello carrello;
}