package it.epicode.shop_libri.libri_e_manga.saga;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@Order(4)
public class sagaRunner implements ApplicationRunner {

    @Autowired
    private SagaService sagaService;
    @Autowired
    private SagaRepository sagaRepository;


    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (sagaRepository.count() == 0){
            List<Request> saghe = Arrays.asList(
                    new Request("Harry Potter", 7),
                    new Request("Il signore degli anelli", 3),
                    new Request("Shadowhunter.The Mortal Instuments", 6),
                    new Request("Cronache del Ghiaccio e del Fuoco",5),
                    new Request("Noragami", 27),
                    new Request("DragonBall", 42),
                    new Request("SailorMoon", 12)
            );

            saghe.forEach(request -> sagaService.create(request));
            System.out.println("--- Saghe inserite ---");
        } else {
            System.out.println("--- Saghe già inserite ---");
        }

        }
    }

