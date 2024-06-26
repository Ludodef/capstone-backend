package it.epicode.shop_libri.libri_e_manga.genere;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@Order(3)
public class GenereRunner implements ApplicationRunner {

    @Autowired
    private GenereRepository genereRepository;
    @Autowired
    private GenereService genereService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if(genereRepository.count() == 0 ){
            List<Request> categorie = Arrays.asList(
                    new Request("Fiction"),
                    new Request("Non-Fiction"),
                    new Request("Science"),
                    new Request("History"),
                    new Request("Biography"),
                    new Request("Fantasy"),
                    new Request("Mystery"),
                    new Request("Romance"),
                    new Request("Horror"),
                    new Request("Self-Help")
            );
            categorie.forEach(request -> genereService.create(request));
            System.out.println("--- generi inseriti ---");
        } else {
            System.out.println("--- Generi già inseriti ---");
        }
        }
    }

