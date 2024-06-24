package it.epicode.shop_hobby.carrello;

import it.epicode.shop_hobby.commons.Prodotto;
import it.epicode.shop_hobby.commons.ProdottoRepository;
import it.epicode.shop_hobby.security.User;
import it.epicode.shop_hobby.security.UserRepository;
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
    private ProdottoRepository prodottoRepository;
    @Autowired
    private UserRepository userRepository;



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
    public Carrello addProdottoToCarrello(Long carrelloId, Long prodottoId, int quantita) {
        Carrello carrello = carrelloRepository.findById(carrelloId)
                .orElseThrow(() -> new EntityNotFoundException("Carrello non trovato con ID: " + carrelloId));

        Prodotto prodotto = prodottoRepository.findById(prodottoId)
                .orElseThrow(() -> new EntityNotFoundException("Prodotto non trovato con ID: " + prodottoId));

        RigaCarrello rigaCarrello = new RigaCarrello();
        rigaCarrello.setCarrello(carrello);
        rigaCarrello.setProdotto(prodotto);
        rigaCarrello.setQuantita(quantita);
        rigaCarrello.setPrezzo(prodotto.getPrezzo() * quantita);

        carrello.getRigheCarrello().add(rigaCarrello);
        return carrelloRepository.save(carrello);
    }

    @Transactional
    public Carrello removeProdottoFromCarrello(Long carrelloId, Long rigaCarrelloId) {
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
    public Carrello updateQuantitaProdotto(Long carrelloId, Long rigaCarrelloId, int nuovaQuantita) {
        Carrello carrello = carrelloRepository.findById(carrelloId)
                .orElseThrow(() -> new EntityNotFoundException("Carrello non trovato con ID: " + carrelloId));

        RigaCarrello rigaCarrello = carrello.getRigheCarrello().stream()
                .filter(riga -> riga.getId().equals(rigaCarrelloId))
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("Riga carrello non trovata con ID: " + rigaCarrelloId));

        rigaCarrello.setQuantita(nuovaQuantita);
        rigaCarrello.setPrezzo(rigaCarrello.getProdotto().getPrezzo() * nuovaQuantita);

        return carrelloRepository.save(carrello);
    }
    @Transactional
    public Carrello svuotaCarrello(Long carrelloId) {
        Carrello carrello = carrelloRepository.findById(carrelloId)
                .orElseThrow(() -> new EntityNotFoundException("Carrello non trovato con ID: " + carrelloId));

        carrello.getRigheCarrello().clear();
        return carrelloRepository.save(carrello);
    }



    public void deleteCarrello(Long id) {
        if (!carrelloRepository.existsById(id)) {
            throw new EntityNotFoundException("Carrello non trovato con ID: " + id);
        }
        carrelloRepository.deleteById(id);
    }

}
