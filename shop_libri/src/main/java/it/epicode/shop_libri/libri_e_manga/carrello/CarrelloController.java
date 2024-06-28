package it.epicode.shop_libri.libri_e_manga.carrello;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carrelli")
public class CarrelloController {

    @Autowired
    private CarrelloService carrelloService;

    @GetMapping
    public List<Carrello> getAllCarrelli() {
        return carrelloService.getAllCarrelli();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Carrello> getCarrelloById(@PathVariable Long id) {
        return carrelloService.getCarrelloById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/user/{userId}")
    public ResponseEntity<Carrello> createCarrelloForUser(@PathVariable Long userId) {
        try {
            Carrello carrello = carrelloService.createCarrelloForUser(userId);
            return new ResponseEntity<>(carrello, HttpStatus.CREATED);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{carrelloId}/add/{prodottoId}")
    public ResponseEntity<Carrello> addProdottoToCarrello(@PathVariable Long carrelloId, @PathVariable Long prodottoId, @RequestParam int quantita) {
        try {
            Carrello carrello = carrelloService.addProdottoToCarrello(carrelloId, prodottoId, quantita);
            return new ResponseEntity<>(carrello, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{carrelloId}/remove/{rigaCarrelloId}")
    public ResponseEntity<Carrello> removeProdottoFromCarrello(@PathVariable Long carrelloId, @PathVariable Long rigaCarrelloId) {
        try {
            Carrello carrello = carrelloService.removeProdottoFromCarrello(carrelloId, rigaCarrelloId);
            return new ResponseEntity<>(carrello, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{carrelloId}/update/{rigaCarrelloId}")
    public ResponseEntity<Carrello> updateQuantitaProdotto(@PathVariable Long carrelloId, @PathVariable Long rigaCarrelloId, @RequestParam int nuovaQuantita) {
        try {
            Carrello carrello = carrelloService.updateQuantitaProdotto(carrelloId, rigaCarrelloId, nuovaQuantita);
            return new ResponseEntity<>(carrello, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("/{carrelloId}/svuota")
    public ResponseEntity<Carrello> svuotaCarrello(@PathVariable Long carrelloId) {
        try {
            Carrello carrello = carrelloService.svuotaCarrello(carrelloId);
            return new ResponseEntity<>(carrello, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCarrello(@PathVariable Long id) {
        try {
            carrelloService.deleteCarrello(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
}
