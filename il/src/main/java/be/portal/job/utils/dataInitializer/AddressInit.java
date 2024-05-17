package be.portal.job.utils.dataInitializer;

import be.portal.job.entities.Address;
import be.portal.job.repositories.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Order(1)
public class AddressInit implements CommandLineRunner {

    private final AddressRepository addressRepository;

    @Override
    public void run(String... args) throws Exception {
        // Créer plusieurs adresses avec des valeurs arbitraires
        List<Address> addresses = List.of(
                new Address("123 Rue de la Liberté", "Bruxelles", 1000, "Belgique"),
                new Address("456 Avenue des Roses", "Paris", 75001, "France"),
                new Address("789 Street of Freedom", "New York", 10001, "USA"),
                new Address("10 Downing Street", "London", 26568, "UK"),
                new Address("Kremlin Embankment", "Moscow", 103073, "Russia"),
                new Address("Unter den Linden", "Berlin", 10117, "Germany"),
                new Address("Tokyo Imperial Palace", "Tokyo", 100-8111, "Japan"),
                new Address("Ulitsa Ilyinka", "Moscow", 109012, "Russia"),
                new Address("Piazza del Colosseo", "Rome", 200184, "Italy"),
                new Address("Plaza de España", "Madrid", 28008, "Spain"),
                new Address("Brooklyn Bridge", "New York", 10038, "USA"),
                new Address("Golden Gate Bridge", "San Francisco", 94129, "USA")
        );

        addressRepository.saveAll(addresses);
    }
}
