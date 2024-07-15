package it.epicode.shop_libri.libri_e_manga.cartacei;

import com.cloudinary.Cloudinary;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/libri")
@CrossOrigin(origins = "http://localhost:4200")
public class CartaceoController {


    @Autowired
    private CartaceoService cartaceoService;
    @Autowired
    private CartaceoRepository repository;
    @Autowired
    private Cloudinary cloudinary;

    //POST
    @PostMapping(value = "create", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<Response> create(
            @RequestPart("libri") @Valid String libriJson,
            @RequestPart(value = "file", required = false) MultipartFile[] files) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();

            Request request = objectMapper.readValue(libriJson, Request.class);
            Response libri = cartaceoService.create(request, files);
            return ResponseEntity.status(HttpStatus.CREATED).body(libri);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Response> modify(@PathVariable Long id, @Valid @RequestBody Request request) {
        try {
            return ResponseEntity.ok(cartaceoService.modify(id, request));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> findCartaceoById(@PathVariable Long id) {
        return ResponseEntity.ok(cartaceoService.findById(id));
    }

    @GetMapping
    public List<Cartaceo> findAllLibri() {
        return cartaceoService.findAll();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteLibro(@PathVariable Long id) {
        return ResponseEntity.ok(cartaceoService.delete(id));
    }

    @PutMapping("/{id}/quantity")
    public ResponseEntity<CompleteResponse> updateProductQuantity(@PathVariable Long id, @RequestBody Map<String, Integer> quantityMap) {
        Integer newQuantity = quantityMap.get("quantity");
        if (newQuantity == null) {
            throw new IllegalArgumentException("Quantity must not be null");
        }
        CompleteResponse updatedProdotto = cartaceoService.updateProductQuantity(id, newQuantity);
        return ResponseEntity.ok(updatedProdotto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response> updateProdotto(@PathVariable Long id, @RequestBody Request request) {
        try {
            return ResponseEntity.ok(cartaceoService.modify(id, request));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/search")
    public ResponseEntity<List<Cartaceo>> findByTitolo(@RequestParam String titolo) {
        List<Cartaceo> cartacei = cartaceoService.findByTitolo(titolo);
        if (cartacei.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(cartacei);
        }
    }
}

