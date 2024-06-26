package it.epicode.shop_libri.libri_e_manga.cartacei;


import org.springframework.beans.factory.annotation.Value;

public interface CartaceoResponsePrj {

        Long getId();

        String getTitolo();

        @Value("#{target.autore.nome}") // l'annotazione Value recupera la posizione dell'elemento richiesto all'interno della classe
        String getNomeAutore();

        @Value("#{target.autore.cognome}") // target e' riferita all' entita principale, seguita poi dalla entita "figlia" e poi dall'attributo richiesto specifico.
        String getCognomeAutore();

        @Value("#{target.casaEditrice.nome}")
        String getNomeCasaEditrice();


}
