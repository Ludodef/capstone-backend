package it.epicode.shop_libri.libri_e_manga.cartacei;

import com.cloudinary.Cloudinary;
import it.epicode.shop_libri.libri_e_manga.security.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/libri")
@RequiredArgsConstructor
public class CartaceoController {

    private final CartaceoService cartaceoService;
    @Autowired
    private CartaceoRepository repository;
    @Autowired
    private Cloudinary cloudinary;

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
    @PostMapping("/{id}/image")
    public ResponseEntity<String> uploadImage(@PathVariable Long id, @RequestParam("file") MultipartFile file) {
        try {
            // Carica l'immagine su Cloudinary
            var uploadResult = cloudinary.uploader().upload(file.getBytes(),
                    com.cloudinary.utils.ObjectUtils.asMap("public_id", id + "_avatar"));

            // Recupera l'URL dell'immagine
            String url = uploadResult.get("url").toString();

            // Aggiorna l'utente con l'URL dell'immagine avatar
            Optional<Cartaceo> userOptional = repository.findById(id);
            if (userOptional.isPresent()) {
                Cartaceo cartaceo = userOptional.get();
                cartaceo.setImmagine(url);
                repository.save(cartaceo);
                return ResponseEntity.ok(url);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
            }
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload avatar");
        }
    }

    @GetMapping("/{id}/image")
    public ResponseEntity<String> getImage(@PathVariable Long id) {
        Optional<Cartaceo> cartaceo = repository.findById(id);
        if (cartaceo.isPresent() && cartaceo.get().getImmagine() != null) {
            return ResponseEntity.ok(cartaceo.get().getImmagine());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Avatar not found");
        }
    }

}
