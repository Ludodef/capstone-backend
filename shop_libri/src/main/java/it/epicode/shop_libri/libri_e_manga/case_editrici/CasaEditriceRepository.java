package it.epicode.shop_libri.libri_e_manga.case_editrici;

import it.epicode.shop_libri.libri_e_manga.autori.AutoreResponsePrj;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CasaEditriceRepository extends JpaRepository<CasaEditrice , Long> {
    public List<CasaEditriceResponsePrj> findAllBy();
}
