package it.epicode.shop_libri.libri_e_manga.cartacei;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@Order(5)
public class CartaceoRunner implements ApplicationRunner {

    @Autowired
    private CartaceoRepository cartaceoRepository;
    @Autowired
    private CartaceoService cartaceoService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (cartaceoRepository.count() == 0) {
            List<Request> cartacei = Arrays.asList(
                    new Request("Il Signore degli anelli", Arrays.asList(1L, 6L),2L,2L, 2L ,TipoCartaceo.LIBRO, 12.80,"La Compagnia dell'Anello si apre nella Contea, un idilliaco paese agricolo dove vivono gli Hobbit,saggi e longevi. La quiete è turbata dall'arrivo dello stregone Gandalf", "...","978-8830105263", 606)
            );
            cartacei.forEach(cartaceoService::create);
            System.out.println("--- Libri inseriti ---");
        }

    }
}
