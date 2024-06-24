package it.epicode.shop_hobby.carrello;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import it.epicode.shop_hobby.security.User;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@Entity
@Table(name = "carrelli")
public class Carrello {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;



    @OneToOne
    @JoinColumn(name = "user_id")
    @JsonIgnoreProperties({"carrello",})
    @ToString.Exclude
    private User user;

    @OneToMany(mappedBy = "carrello", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties({"carrello",})
    @ToString.Exclude
    private List<RigaCarrello> righeCarrello;

}
