package it.epicode.shop_hobby.libri.libro;

import it.epicode.shop_hobby.libri.genere.Genere;
import it.epicode.shop_hobby.libri.genere.Response;

import java.util.List;

public class CompleteResponse {
    private Long id;
    private String titolo;
    private it.epicode.shop_hobby.libri.autore.Response autore;
    private List<Genere> genere;
    private it.epicode.shop_hobby.libri.casa_editrice.Response casaEditrice;

    public it.epicode.shop_hobby.libri.autore.Response getAutore() {
        return autore;
    }

    public List<Genere> getGenere() {
        return genere;
    }

    public it.epicode.shop_hobby.libri.casa_editrice.Response getCasaEditrice() {
        return casaEditrice;
    }

    public void setGenere(List<Genere> genere) {
        this.genere = genere;
    }

    public void setAutore(it.epicode.shop_hobby.libri.autore.Response autore) {
        this.autore = autore;
    }

    public void setCasaEditrice(it.epicode.shop_hobby.libri.casa_editrice.Response casaEditrice) {
        this.casaEditrice = casaEditrice;
    }
}
