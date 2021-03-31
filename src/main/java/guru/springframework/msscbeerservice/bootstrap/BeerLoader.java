package guru.springframework.msscbeerservice.bootstrap;

import guru.springframework.msscbeerservice.domain.Beer;
import guru.springframework.msscbeerservice.repositories.BeerRepository;
import guru.springframework.msscbeerservice.web.model.BeerStyleEnum;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.UUID;

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
                    .beerStyle(BeerStyleEnum.IPA.name())
                    .upc(BEER_1_UPC)
                    .price(new BigDecimal("12.95"))
                    .quantityToBrew(200)
                    .minOnHand(12)
                    .build();

            Beer beer2 = Beer.builder()
                    .beerName("Galaxy Cat")
                    .beerStyle(BeerStyleEnum.PALE_ALE.name())
                    .upc(BEER_2_UPC)
                    .price(new BigDecimal("11.95"))
                    .quantityToBrew(200)
                    .minOnHand(12)
                    .build();

            Beer beer3 = Beer.builder()
                    .beerName("Pinball Porter")
                    .beerStyle(BeerStyleEnum.PALE_ALE.name())
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
