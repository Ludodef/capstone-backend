package it.epicode.shop_hobby.manga.manga;

import it.epicode.shop_hobby.manga.autore_manga.AutoreManga;
import it.epicode.shop_hobby.manga.casa_editrice_manga.CasaEditriceManga;
import it.epicode.shop_hobby.manga.saghe_manga.SagaManga;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MangaRepository extends JpaRepository<Manga , Long > {
    //queryMethod per cercare via titoloManga
    public Manga findByTitoloManga(String titoloManga);
    //queryMethod per cercare via autore
    public List<Manga> findByAutoreManga(AutoreManga autoreManga);
    public List<Manga> findByCasaEditriceManga(CasaEditriceManga casaEditriceManga);
    public List<Manga> findBySagaManga(SagaManga sagaManga);
}
