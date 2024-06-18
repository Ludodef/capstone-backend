package it.epicode.shop_hobby.libri.casa_editrice;

import it.epicode.shop_hobby.libri.autore.Autore;
import it.epicode.shop_hobby.libri.autore.AutoreService;
import it.epicode.shop_hobby.libri.casa_editrice.Request;
import it.epicode.shop_hobby.libri.casa_editrice.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/case_editrici")
public class CasaEditriceController {

    @Autowired
    CasaEditriceService service;

    @GetMapping("/{id}")
    public ResponseEntity<Response> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findbyId(id));

    }

    @GetMapping
    public ResponseEntity<List<CasaEditrice>> findAll() {
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
