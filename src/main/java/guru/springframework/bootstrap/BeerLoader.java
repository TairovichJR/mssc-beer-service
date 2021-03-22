package guru.springframework.bootstrap;

import guru.springframework.domain.Beer;
import guru.springframework.repositories.BeerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Arrays;

@Component
public class BeerLoader implements CommandLineRunner {

    public static final String BEER_1_UPC = "0631234200036";
    public static final String BEER_2_UPC = "0631234300019";
    public static final String BEER_3_UPC = "0083783375213";

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
                    .upc(BEER_1_UPC)
                    .price(new BigDecimal("12.95"))
                    .quantityToBrew(200)
                    .minOnHand(12)
                    .build();

            Beer beer2 = Beer.builder()
                    .beerName("Galaxy Cat")
                    .beerStyle("PALE_ALE")
                    .upc(BEER_2_UPC)
                    .price(new BigDecimal("11.95"))
                    .quantityToBrew(200)
                    .minOnHand(12)
                    .build();

            Beer beer3 = Beer.builder()
                    .beerName("No Hammers on the Bar")
                    .beerStyle("PALE_ALE")
                    .upc(BEER_3_UPC)
                    .price(new BigDecimal("15.95"))
                    .quantityToBrew(200)
                    .minOnHand(12)
                    .build();

            repository.saveAll(Arrays.asList(beer1, beer2, beer3));
        }

        System.out.println("Loaded Beers: " + repository.count());
    }
}
