package com.allianz.healthtourism.util.data;

import com.allianz.healthtourism.database.entity.City;
import com.allianz.healthtourism.database.entity.Country;
import com.allianz.healthtourism.database.entity.Flight;
import com.allianz.healthtourism.database.entity.Hotel;
import com.allianz.healthtourism.database.repository.CityRepository;
import com.allianz.healthtourism.database.repository.CountryRepository;
import com.allianz.healthtourism.database.repository.FlightRepository;
import com.allianz.healthtourism.database.repository.HotelRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.Month;

@Component
public class DataSeeder implements CommandLineRunner {


    private final CityRepository cityRepository;
    private final CountryRepository countryRepository;
    private final FlightRepository flightRepository;
    private final HotelRepository hotelRepository;


    public DataSeeder(CityRepository cityRepository, CountryRepository countryRepository, FlightRepository flightRepository, HotelRepository hotelRepository) {
        this.cityRepository = cityRepository;
        this.countryRepository = countryRepository;
        this.flightRepository = flightRepository;
        this.hotelRepository = hotelRepository;
    }


    @Override
    public void run(String... args){
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

            Flight cologneToAnkaraFlight = new Flight();
            cologneToAnkaraFlight.setDepartureCity(cologne);
            cologneToAnkaraFlight.setArrivalCity(ankara);
            LocalDateTime departureTime = LocalDateTime.of(2023, Month.OCTOBER, 10, 8, 0);
            cologneToAnkaraFlight.setDepartureTime(departureTime);
            LocalDateTime arrivalTime = LocalDateTime.of(2023, Month.OCTOBER, 10, 12, 0);
            cologneToAnkaraFlight.setArrivalTime(arrivalTime);
            cologneToAnkaraFlight.setTotalSeatCount(40);
            cologneToAnkaraFlight.setEmptySeatCount(10);
            flightRepository.save(cologneToAnkaraFlight);

            Flight ankaraToCologneFlight = new Flight();
            ankaraToCologneFlight.setDepartureCity(ankara);
            ankaraToCologneFlight.setArrivalCity(cologne);
            departureTime = LocalDateTime.of(2023, Month.OCTOBER, 12, 8, 0);
            ankaraToCologneFlight.setDepartureTime(departureTime);
            arrivalTime = LocalDateTime.of(2023, Month.OCTOBER, 12, 12, 0);
            ankaraToCologneFlight.setArrivalTime(arrivalTime);
            ankaraToCologneFlight.setTotalSeatCount(40);
            ankaraToCologneFlight.setEmptySeatCount(20);
            flightRepository.save(ankaraToCologneFlight);

            Hotel ankaraHotel = new Hotel();
            ankaraHotel.setCity(ankara);
            ankaraHotel.setEmptyRoomCount(15);
            ankaraHotel.setTotalRoomCount(50);
            LocalDateTime checkInTime = LocalDateTime.of(2023, Month.OCTOBER, 10, 13, 0);
            ankaraHotel.setCheckIn(checkInTime);
            LocalDateTime checkOutTime = LocalDateTime.of(2023, Month.OCTOBER, 12, 7, 0);
            ankaraHotel.setCheckOut(checkOutTime);
            hotelRepository.save(ankaraHotel);

        }

    }
}

