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
                   new Request("La compagnia dell'anello", "Avventura/fantasy","J.R.R Tolkien", "Bompiani", "Il signore degli anelli",12.80, "Primo libro","https://res.cloudinary.com/dfm9lzhxy/image/upload/v1719679457/2_avatar.jpg\n","1234567890",600,4L, 100),
                    new Request("Le due torri", "Avventura/fantasy","J.R.R Tolkien", "Bompiani", "Il signore degli anelli",12.80, "Secondo libro","https://res.cloudinary.com/dfm9lzhxy/image/upload/v1719679457/2_avatar.jpg\n","123456789000",600,4L, 100),
                    new Request("Il ritorno del re", "Avventura/fantasy","J.R.R Tolkien", "Bompiani", "Il signore degli anelli",12.80, "terzo libro","https://res.cloudinary.com/dfm9lzhxy/image/upload/v1719679457/2_avatar.jpg\n","1234567890",600,4L, 100)
            );
            cartacei.forEach(cartaceoService::create);
            System.out.println("--- Libri inseriti ---");
        }

    }
}
