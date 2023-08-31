package com.allianz.healthtourism.util;

import com.allianz.healthtourism.database.entity.City;
import com.allianz.healthtourism.database.entity.Country;
import com.allianz.healthtourism.database.entity.Patient;
import com.allianz.healthtourism.database.repository.CityRepository;
import com.allianz.healthtourism.database.repository.CountryRepository;
import com.allianz.healthtourism.database.repository.FlightRepository;
import com.allianz.healthtourism.database.repository.HotelRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;

@Component
public class DataSeeder implements CommandLineRunner {


    private final CityRepository cityRepository;
    private final CountryRepository countryRepository;


    public DataSeeder(CityRepository cityRepository, CountryRepository countryRepository) {
        this.cityRepository = cityRepository;
        this.countryRepository = countryRepository;

    }


    @Override
    public void run(String... args) throws Exception {
        if (countryRepository.findAll().isEmpty() && cityRepository.findAll().isEmpty()) {
            Country turkey = new Country();
            turkey.setName("Turkey");
            Country savedTurkey = countryRepository.save(turkey);
            Country germany = new Country();
            germany.setName("Germany");
            Country savedGermany = countryRepository.save(germany);
            Country france = new Country();
            france.setName("France");
            Country savedFrance = countryRepository.save(france);
            Country belgium = new Country();
            belgium.setName("Belgium");
            Country savedBelgium = countryRepository.save(belgium);

            City ankara = new City();
            ankara.setName("Ankara");
            ankara.setCountry(savedTurkey);
            cityRepository.save(ankara);
            City cologne = new City();
            cologne.setName("Köln");
            cologne.setCountry(savedGermany);
            cityRepository.save(cologne);
            City paris = new City();
            paris.setName("Paris");
            paris.setCountry(savedFrance);
            cityRepository.save(paris);
            City brussels = new City();
            brussels.setName("Brüssels");
            brussels.setCountry(savedBelgium);
            cityRepository.save(brussels);

        }
    }
}
