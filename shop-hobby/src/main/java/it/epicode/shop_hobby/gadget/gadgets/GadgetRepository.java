package it.epicode.shop_hobby.gadget.gadgets;

import it.epicode.shop_hobby.manga.autore_manga.AutoreManga;
import it.epicode.shop_hobby.manga.casa_editrice_manga.CasaEditriceManga;
import it.epicode.shop_hobby.manga.manga.Manga;
import it.epicode.shop_hobby.manga.saghe_manga.SagaManga;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GadgetRepository extends JpaRepository<Gadget , Long > {
    public Gadget findByNome(String nome);

}
