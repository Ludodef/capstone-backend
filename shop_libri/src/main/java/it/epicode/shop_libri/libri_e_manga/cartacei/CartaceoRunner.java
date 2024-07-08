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
                    new Request("La Compagnia dell'anello" , "Avventura", "Bompiani","Il signore degli anelli", TipoCartaceo.LIBRO , 12.80, "Primo libro", "..." , "1234567890" , 620),
                    new Request("Le due torri" , "Avventura", "Bompiani","Il signore degli anelli", TipoCartaceo.LIBRO , 12.80, "Secondo libro", "..." , "123456bnt7890" , 620),
                    new Request("Il ritorno del re" , "Avventura", "Bompiani","Il signore degli anelli", TipoCartaceo.LIBRO , 12.80, "Terzo libro", "..." , "12345ghhjtg67890" , 620)
            );
            cartacei.forEach(cartaceoService::create);
            System.out.println("--- Libri inseriti ---");
        }

    }
}
