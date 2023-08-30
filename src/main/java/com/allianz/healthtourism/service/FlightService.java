package com.allianz.healthtourism.service;

import com.allianz.healthtourism.database.entity.City;
import com.allianz.healthtourism.database.entity.Flight;
import com.allianz.healthtourism.database.repository.CityRepository;
import com.allianz.healthtourism.database.repository.FlightRepository;
import com.allianz.healthtourism.database.specification.FlightSpecification;
import com.allianz.healthtourism.mapper.CityMapper;
import com.allianz.healthtourism.mapper.FlightMapper;
import com.allianz.healthtourism.model.CityDTO;
import com.allianz.healthtourism.model.FlightDTO;
import com.allianz.healthtourism.model.requestDTO.FlightRequestDTO;
import com.allianz.healthtourism.util.BaseService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class FlightService extends BaseService<Flight, FlightDTO, FlightRequestDTO, FlightRepository, FlightMapper,
        FlightSpecification> {

    private final FlightRepository flightRepository;
    private final FlightMapper flightMapper;
    private final CityRepository cityRepository;


    public FlightService(FlightRepository repository, FlightMapper mapper, FlightSpecification specification,
                         FlightRepository flightRepository,
                         FlightMapper flightMapper, CityRepository cityRepository) {
        super(repository, mapper, specification);
        this.flightRepository = flightRepository;

        this.flightMapper = flightMapper;

        this.cityRepository = cityRepository;
    }

    @Override
    public FlightDTO save(FlightRequestDTO requestDTO) {
        Flight flight = flightMapper.requestDtoToEntity(requestDTO);
        populateFlightWithCitiesAndSave(flight, requestDTO);
        return flightMapper.entityToDto(flight);
    }

    @Override
    public FlightDTO update(UUID uuid, FlightRequestDTO requestDTO) {
        Flight flight = flightRepository.findByUuid(uuid).orElse(null);
        if (flight != null) {
            flight = flightMapper.requestDtoToExistEntity(requestDTO, flight);
            populateFlightWithCitiesAndSave(flight, requestDTO);
            return flightMapper.entityToDto(flight);
        } else {
            return null;
        }
    }

    private void populateFlightWithCitiesAndSave(Flight flight, FlightRequestDTO requestDTO) {
        City departureCity = cityRepository.findByUuid(requestDTO.getDepartureCityUUID()).orElse(null);
        City arrivalCity = cityRepository.findByUuid(requestDTO.getArrivalCityUUID()).orElse(null);
        if (departureCity != null) {
            flight.setDepartureCity(departureCity);
        }
        if (arrivalCity != null) {
            flight.setArrivalCity(arrivalCity);
        }
        flightRepository.save(flight);
    }
}
