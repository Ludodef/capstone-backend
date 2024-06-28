package it.epicode.shop_libri.libri_e_manga.case_editrici;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/case_editrici")
public class CasaEditriceController {

    @Autowired
    CasaEditriceService service;


    @GetMapping("/{id}")
    public ResponseEntity<Response> findById(@PathVariable Long id){
        return ResponseEntity.ok(service.findById(id));

    }

    @GetMapping
    public ResponseEntity<List<CasaEditrice>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @PostMapping
    public ResponseEntity<Response> create(@RequestBody Request request){
        return ResponseEntity.ok(service.create(request));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Response> modify(@PathVariable Long id, @RequestBody Request request){
        return ResponseEntity.ok(service.modify(id, request));
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        return ResponseEntity.ok(service.delete(id));
    }
}
