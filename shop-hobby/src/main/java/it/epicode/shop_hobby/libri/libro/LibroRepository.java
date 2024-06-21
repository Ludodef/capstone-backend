package it.epicode.shop_hobby.libri.libro;

import it.epicode.shop_hobby.libri.autore.Autore;
import it.epicode.shop_hobby.libri.casa_editrice.CasaEditrice;
import it.epicode.shop_hobby.libri.saghe.Saga;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LibroRepository extends JpaRepository<Libro , Long> {
    //queryMethod per cercare via titolo
    public Libro findByTitolo(String titolo);
    //queryMethod per cercare via autore
    public List<Libro> findByAutore(Autore autore);
    public List<Libro> findByCasaEditrice(CasaEditrice editrice);
    public List<Libro> findBySaga (Saga saga);
}
