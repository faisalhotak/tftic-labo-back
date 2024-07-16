package be.portal.job.utils.datainitializer;

import be.portal.job.entities.ZipCity;
import be.portal.job.repositories.ZipCityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
@Order(1)
public class ZipCityInit implements CommandLineRunner {

    private final ZipCityRepository zipCityRepository;

    @Override
    public void run(String... args) throws Exception {
        List<ZipCity> zipCities = new ArrayList<>();

        zipCities.add(new ZipCity("1000", "Brussels"));
        zipCities.add(new ZipCity("1300", "Wavre"));
        zipCities.add(new ZipCity("1348", "Louvain-la-Neuve"));
        zipCities.add(new ZipCity("1370", "Jodoigne"));
        zipCities.add(new ZipCity("1400", "Nivelles"));
        zipCities.add(new ZipCity("1410", "Waterloo"));
        zipCities.add(new ZipCity("1420", "Braine-l'Alleud"));
        zipCities.add(new ZipCity("1470", "Genappe"));
        zipCities.add(new ZipCity("1500", "Halle"));
        zipCities.add(new ZipCity("1650", "Beersel"));
        zipCities.add(new ZipCity("2000", "Antwerp"));
        zipCities.add(new ZipCity("2200", "Herentals"));
        zipCities.add(new ZipCity("2220", "Heist-op-den-Berg"));
        zipCities.add(new ZipCity("2300", "Turnhout"));
        zipCities.add(new ZipCity("2440", "Geel"));
        zipCities.add(new ZipCity("2800", "Mechelen"));
        zipCities.add(new ZipCity("2930", "Brasschaat"));
        zipCities.add(new ZipCity("3000", "Leuven"));
        zipCities.add(new ZipCity("3200", "Aarschot"));
        zipCities.add(new ZipCity("3290", "Diest"));
        zipCities.add(new ZipCity("3300", "Tienen"));
        zipCities.add(new ZipCity("3500", "Hasselt"));
        zipCities.add(new ZipCity("3580", "Beringen"));
        zipCities.add(new ZipCity("3600", "Genk"));
        zipCities.add(new ZipCity("3680", "Maaseik"));
        zipCities.add(new ZipCity("3700", "Tongeren"));
        zipCities.add(new ZipCity("3740", "Bilzen"));
        zipCities.add(new ZipCity("3840", "Borgloon"));
        zipCities.add(new ZipCity("3920", "Lommel"));
        zipCities.add(new ZipCity("3990", "Peer"));
        zipCities.add(new ZipCity("4000", "Liège"));
        zipCities.add(new ZipCity("4040", "Herstal"));
        zipCities.add(new ZipCity("4050", "Chaudfontaine"));
        zipCities.add(new ZipCity("4100", "Seraing"));
        zipCities.add(new ZipCity("4280", "Hannut"));
        zipCities.add(new ZipCity("4420", "Saint-Nicolas"));
        zipCities.add(new ZipCity("4500", "Huy"));
        zipCities.add(new ZipCity("4700", "Eupen"));
        zipCities.add(new ZipCity("4800", "Verviers"));
        zipCities.add(new ZipCity("4880", "Aubel"));
        zipCities.add(new ZipCity("4970", "Stavelot"));
        zipCities.add(new ZipCity("5000", "Namur"));
        zipCities.add(new ZipCity("5030", "Gembloux"));
        zipCities.add(new ZipCity("5070", "Fosses-la-Ville"));
        zipCities.add(new ZipCity("5300", "Andenne"));
        zipCities.add(new ZipCity("5570", "Beauraing"));
        zipCities.add(new ZipCity("5590", "Ciney"));
        zipCities.add(new ZipCity("5600", "Philippeville"));
        zipCities.add(new ZipCity("6000", "Charleroi"));
        zipCities.add(new ZipCity("6041", "Gosselies"));
        zipCities.add(new ZipCity("6180", "Courcelles"));
        zipCities.add(new ZipCity("6200", "Châtelet"));
        zipCities.add(new ZipCity("6460", "Chimay"));
        zipCities.add(new ZipCity("6600", "Bastogne"));
        zipCities.add(new ZipCity("6700", "Arlon"));
        zipCities.add(new ZipCity("6760", "Virton"));
        zipCities.add(new ZipCity("6820", "Florenville"));
        zipCities.add(new ZipCity("6830", "Bouillon"));
        zipCities.add(new ZipCity("6840", "Neufchâteau"));
        zipCities.add(new ZipCity("6870", "Saint-Hubert"));
        zipCities.add(new ZipCity("6900", "Marche-en-Famenne"));
        zipCities.add(new ZipCity("6940", "Durbuy"));
        zipCities.add(new ZipCity("7000", "Mons"));
        zipCities.add(new ZipCity("7060", "Soignies"));
        zipCities.add(new ZipCity("7090", "Braine-le-Comte"));
        zipCities.add(new ZipCity("7100", "La Louvière"));
        zipCities.add(new ZipCity("7130", "Binche"));
        zipCities.add(new ZipCity("7500", "Tournai"));
        zipCities.add(new ZipCity("7700", "Mouscron"));
        zipCities.add(new ZipCity("7780", "Comines-Warneton"));
        zipCities.add(new ZipCity("7800", "Ath"));
        zipCities.add(new ZipCity("7850", "Enghien"));
        zipCities.add(new ZipCity("8000", "Bruges"));
        zipCities.add(new ZipCity("8300", "Knokke-Heist"));
        zipCities.add(new ZipCity("8370", "Blankenberge"));
        zipCities.add(new ZipCity("8400", "Oostende"));
        zipCities.add(new ZipCity("8470", "Gistel"));
        zipCities.add(new ZipCity("8500", "Courtrai"));
        zipCities.add(new ZipCity("8530", "Harelbeke"));
        zipCities.add(new ZipCity("8580", "Avelgem"));
        zipCities.add(new ZipCity("8600", "Diksmuide"));
        zipCities.add(new ZipCity("8790", "Waregem"));
        zipCities.add(new ZipCity("8800", "Roeselare"));
        zipCities.add(new ZipCity("8870", "Izegem"));
        zipCities.add(new ZipCity("8900", "Ypres"));
        zipCities.add(new ZipCity("8930", "Menen"));
        zipCities.add(new ZipCity("8940", "Wervik"));
        zipCities.add(new ZipCity("8970", "Poperinge"));
        zipCities.add(new ZipCity("9000", "Ghent"));
        zipCities.add(new ZipCity("9100", "Sint-Niklaas"));
        zipCities.add(new ZipCity("9160", "Lokeren"));
        zipCities.add(new ZipCity("9200", "Dendermonde"));
        zipCities.add(new ZipCity("9300", "Aalst"));
        zipCities.add(new ZipCity("9600", "Ronse"));
        zipCities.add(new ZipCity("9700", "Oudenaarde"));
        zipCities.add(new ZipCity("9800", "Deinze"));
        zipCities.add(new ZipCity("9900", "Eeklo"));
        zipCities.add(new ZipCity("9940", "Evergem"));

        zipCityRepository.saveAll(zipCities);
    }
}
