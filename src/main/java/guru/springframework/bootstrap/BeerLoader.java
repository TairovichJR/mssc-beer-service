package guru.springframework.bootstrap;

import guru.springframework.domain.Beer;
import guru.springframework.repositories.BeerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class BeerLoader implements CommandLineRunner {

    private final BeerRepository repository;

    public BeerLoader(BeerRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadBeer();
    }

    public void loadBeer() throws InterruptedException {
        if (repository.count() == 0){
            Beer beer1 = Beer.builder()
                    .beerName("Mango Bobs")
                    .beerStyle("IPA")
                    .upc(1230000001L)
                    .price(new BigDecimal("12.95"))
                    .quantityToBrew(200)
                    .minOnHand(12)
                    .build();
            repository.save(beer1);

            Beer beer2 = Beer.builder()
                    .beerName("Galaxy Cat")
                    .beerStyle("PALE_ALE")
                    .upc(1230000002L)
                    .price(new BigDecimal("11.95"))
                    .quantityToBrew(200)
                    .minOnHand(12)
                    .build();

            repository.save(beer2);
        }

        System.out.println("Loaded Beers: " + repository.count());
    }
}
