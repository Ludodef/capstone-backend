package it.epicode.shop_libri.libri_e_manga.carrello;

import it.epicode.shop_libri.libri_e_manga.cartacei.Cartaceo;
import it.epicode.shop_libri.libri_e_manga.cartacei.CartaceoRepository;
import it.epicode.shop_libri.libri_e_manga.email.EmailService;
import it.epicode.shop_libri.libri_e_manga.security.User;
import it.epicode.shop_libri.libri_e_manga.security.UserRespository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarrelloService {

    @Autowired
    private CarrelloRepository carrelloRepository;
    @Autowired
    private CartaceoRepository cartaceoRepository;
    @Autowired
    private UserRespository userRepository;


    public List<Carrello> getAllCarrelli() {
        return carrelloRepository.findAll();
    }

    public Optional<Carrello> getCarrelloById(Long id) {
        return carrelloRepository.findById(id);
    }

    @Transactional
    public Carrello createCarrelloForUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User non trovato con ID: " + userId));

        Carrello carrello = new Carrello();
        carrello.setUser(user);

        return carrelloRepository.save(carrello);
    }

    @Transactional
    public Carrello addCartaceoToCarrello(Long carrelloId, Long cartaceoId, int quantita) {
        Carrello carrello = carrelloRepository.findById(carrelloId)
                .orElseThrow(() -> new EntityNotFoundException("Carrello non trovato con ID: " + carrelloId));

        Cartaceo cartaceo = cartaceoRepository.findById(cartaceoId)
                .orElseThrow(() -> new EntityNotFoundException("Prodotto non trovato con ID: " + cartaceoId));

        RigaCarrello rigaCarrello = new RigaCarrello();
        rigaCarrello.setCarrello(carrello);
        rigaCarrello.setCartaceo(cartaceo);
        rigaCarrello.setQuantita(quantita);
        rigaCarrello.setPrezzo(cartaceo.getPrezzo() * quantita);

        carrello.getRigheCarrello().add(rigaCarrello);
        ;
        return carrelloRepository.save(carrello);
    }

    @Transactional
    public Carrello removeCartaceoFromCarrello(Long carrelloId, Long rigaCarrelloId) {
        Carrello carrello = carrelloRepository.findById(carrelloId)
                .orElseThrow(() -> new EntityNotFoundException("Carrello non trovato con ID: " + carrelloId));

        RigaCarrello rigaCarrello = carrello.getRigheCarrello().stream()
                .filter(riga -> riga.getId().equals(rigaCarrelloId))
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("Riga carrello non trovata con ID: " + rigaCarrelloId));

        carrello.getRigheCarrello().remove(rigaCarrello);
        return carrelloRepository.save(carrello);
    }

    @Transactional
    public Carrello updateQuantitaCartaceo(Long carrelloId, Long rigaCarrelloId, int nuovaQuantita) {
        Carrello carrello = carrelloRepository.findById(carrelloId)
                .orElseThrow(() -> new EntityNotFoundException("Carrello non trovato con ID: " + carrelloId));

        RigaCarrello rigaCarrello = carrello.getRigheCarrello().stream()
                .filter(riga -> riga.getId().equals(rigaCarrelloId))
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("Riga carrello non trovata con ID: " + rigaCarrelloId));

        rigaCarrello.setQuantita(nuovaQuantita);
        rigaCarrello.setPrezzo(rigaCarrello.getCartaceo().getPrezzo() * nuovaQuantita);


        return carrelloRepository.save(carrello);
    }

    @Transactional
    public Carrello svuotaCarrello(Long carrelloId) {
        Carrello carrello = carrelloRepository.findById(carrelloId)
                .orElseThrow(() -> new EntityNotFoundException("Carrello non trovato con ID: " + carrelloId));

        carrello.getRigheCarrello().clear();
        Carrello savedCarrello = carrelloRepository.save(carrello);

        // Ottenere l'email dell'utente
        //User user = carrello.getUser();
        //String email = user.getEmail();

        // Inviare email di conferma acquisto
        //emailService.sendPurchaseConfirmationEmail(email);

        return savedCarrello;
    }

    public void deleteCarrello(Long id) {
        if (!carrelloRepository.existsById(id)) {
            throw new EntityNotFoundException("Carrello non trovato con ID: " + id);
        }
        carrelloRepository.deleteById(id);
    }
}
