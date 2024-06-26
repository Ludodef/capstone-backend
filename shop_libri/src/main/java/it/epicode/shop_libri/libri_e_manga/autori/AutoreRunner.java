package it.epicode.shop_libri.libri_e_manga.autori;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@Order(1)
public class AutoreRunner implements ApplicationRunner {

    @Autowired
    private AutoreRepository autoreRepository;

    @Autowired
    private AutoreService autoreService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (autoreRepository.count() == 0) {
            List<Request> autori = Arrays.asList(
                    new Request("J.K", "Rowling"),
                    new Request("J.R.R", "Tolkien"),
                    new Request("Cassandra", "Clare"),
                    new Request("George R.R", "Martin"),
                    new Request("Adachi", "Toka"),
                    new Request("Akira", "Toriyama"),
                    new Request("Naoko", "Takeuchi")
            );
            autori.forEach(request -> autoreService.create(request));
            System.out.println("--- Autori inseriti ---");
        } else {
            System.out.println("--- Autori già inseriti ---");
        }
        }
    }

