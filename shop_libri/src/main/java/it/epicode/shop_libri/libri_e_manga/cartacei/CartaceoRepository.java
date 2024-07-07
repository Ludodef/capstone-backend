package it.epicode.shop_libri.libri_e_manga.cartacei;

import it.epicode.shop_libri.libri_e_manga.autori.Autore;
import it.epicode.shop_libri.libri_e_manga.case_editrici.CasaEditrice;
import it.epicode.shop_libri.libri_e_manga.saga.Saga;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartaceoRepository extends JpaRepository<Cartaceo , Long> {
    public Cartaceo findByTitolo(String titolo);
    public List<Cartaceo> findByAutore(Autore autore);
    public List<Cartaceo> findByCasaEditrice(CasaEditrice casaEditrice);

    public List<Cartaceo> findBySaga(Saga saga);

    public boolean existsByTitolo(String titolo);


    public List<Cartaceo> findAllBy();
}
