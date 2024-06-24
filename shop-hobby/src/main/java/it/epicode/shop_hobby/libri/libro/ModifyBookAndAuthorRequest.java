package it.epicode.shop_hobby.libri.libro;

import lombok.Data;

@Data
public class ModifyBookAndAuthorRequest {
    private String titolo;
    private String nomeAutore;
}
