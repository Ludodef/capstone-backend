package it.epicode.shop_libri.libri_e_manga.case_editrici;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@Order(2)
public class CasaEditriceRunner implements ApplicationRunner {

    @Autowired
    private CasaEditriceRepository casaEditriceRepository;

    @Autowired
    private CasaEditriceService casaEditriceService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if(casaEditriceRepository.count() == 0){
            List<Request> caseEditrici = Arrays.asList(
                    new Request("Mondadori"),
                    new Request("Bompiani"),
                    new Request("Sonzogno"),
                    new Request("Feltrinelli"),
                    new Request("HarperCollins"),
                    new Request("StarComics"),
                    new Request("Panini"),
                    new Request("J-Pop")
            );
            caseEditrici.forEach(request -> casaEditriceService.create(request));
            System.out.println("--- Case Editrici inserite --- ");
        } else {
            System.out.println("--- Case Editrici gia' inserite --- ");
        }
        }

    }

