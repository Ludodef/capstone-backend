package it.epicode.shop_hobby.gadget.gadgets;

import lombok.Data;

import java.util.List;

@Data

public class Request {
    private Long id;

    private String nome;
    private String descrizione;
    private double prezzo;
    private String image;
    private List<Long> idCategoria;

    public Iterable<Long> getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(List<Long> idCategoria) {
        this.idCategoria = idCategoria;
    }
}
