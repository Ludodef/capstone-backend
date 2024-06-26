package it.epicode.shop_libri.libri_e_manga.autori;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AutoreRepository extends JpaRepository<Autore, Long> {
    public List<AutoreResponsePrj> findAllBy();

    public List<Autore> findAllByNome(String nome);

}
