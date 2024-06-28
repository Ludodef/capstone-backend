package it.epicode.shop_libri.libri_e_manga.cartacei;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/libri")
@RequiredArgsConstructor
public class CartaceoController {

    private final CartaceoService cartaceoService;

    @PostMapping
    public ResponseEntity<Response> create(@Valid @RequestBody Request request) {
        Response response = cartaceoService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Response> modify(@PathVariable Long id, @Valid @RequestBody Request request) {
        Response response = cartaceoService.modify(id, request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompleteResponse> findCartaceoById(@PathVariable Long id) {
        CompleteResponse response = cartaceoService.findById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<CartaceoResponsePrj>> findAllLibri() {
        List<CartaceoResponsePrj> response = cartaceoService.findAll();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteLibro(@PathVariable Long id) {
        String message = cartaceoService.delete(id);
        return ResponseEntity.ok(message);
    }


}
