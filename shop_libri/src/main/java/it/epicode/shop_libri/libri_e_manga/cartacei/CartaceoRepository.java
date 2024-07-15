package it.epicode.shop_libri.libri_e_manga.cartacei;


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartaceoRepository extends JpaRepository<Cartaceo , Long> {
    List<Cartaceo> findByTitoloContainingIgnoreCase(String titolo);

    public boolean existsByTitolo(String titolo);


    public List<Cartaceo> findAllBy();
}
