package it.epicode.shop_hobby.manga.autore_manga;

import it.epicode.shop_hobby.libri.autore.Autore;
import it.epicode.shop_hobby.libri.autore.AutoreService;
import it.epicode.shop_hobby.manga.autore_manga.Request;
import it.epicode.shop_hobby.manga.autore_manga.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/autori_manga")
public class AutoreMangaController {

    @Autowired
    AutoreMangaService service;

    @GetMapping("/{id}")
    public ResponseEntity<Response> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findbyId(id));

    }

    @GetMapping
    public ResponseEntity<List<AutoreManga>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @PostMapping
    public ResponseEntity<Response> create(@RequestBody Request request) {
        return ResponseEntity.ok(service.create(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response> modify(@PathVariable Long id, @RequestBody Request request) {
        return ResponseEntity.ok(service.modify(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        return ResponseEntity.ok(service.delete(id));
    }
}
