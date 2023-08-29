package com.allianz.healthtourism.service;

import com.allianz.healthtourism.database.entity.City;
import com.allianz.healthtourism.database.entity.Flight;
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
    private final CityService cityService;
    private final CityMapper cityMapper;

    public FlightService(FlightRepository repository, FlightMapper mapper, FlightSpecification specification,
                         FlightRepository flightRepository,
                         FlightMapper flightMapper, CityService cityService, CityMapper cityMapper) {
        super(repository, mapper, specification);
        this.flightRepository = flightRepository;

        this.flightMapper = flightMapper;
        this.cityService = cityService;
        this.cityMapper = cityMapper;
    }

    @Override
    public FlightDTO save(FlightRequestDTO requestDTO) {
        Flight flight = new Flight();
        CityDTO departureCityDTO = cityService.getByUUID(requestDTO.getDepartureCityUUID());
        CityDTO arrivalCityDTO = cityService.getByUUID(requestDTO.getArrivalCityUUID());
        flight.setDepartureCity(cityMapper.dtoToEntity(departureCityDTO));
        flight.setArrivalCity(cityMapper.dtoToEntity(arrivalCityDTO));
        flight.setDepartureTime(requestDTO.getDepartureTime());
        flight.setLandingTime(requestDTO.getLandingTime());
        flight.setTotalSeatCount(requestDTO.getTotalSeatCount());
        flight.setEmptySeatCount(requestDTO.getEmptySeatCount());
        flightRepository.save(flight);
        return flightMapper.entityToDto(flight);
    }

    @Override
    public FlightDTO update(UUID uuid, FlightRequestDTO requestDTO) {
        Flight flight = flightRepository.findByUuid(uuid).orElse(null);
        if (flight != null) {
            Flight flightToSave = flightMapper.requestDtoToExistEntity(requestDTO, flight);
            CityDTO arrivalCity = cityService.getByUUID(requestDTO.getArrivalCityUUID());
            CityDTO departureCity = cityService.getByUUID(requestDTO.getDepartureCityUUID());
            flightToSave.setArrivalCity(cityMapper.dtoToEntity(arrivalCity));
            flightToSave.setDepartureCity(cityMapper.dtoToEntity(departureCity));
            flightRepository.save(flightToSave);
            return flightMapper.entityToDto(flightToSave);
        } else {
            return null;
        }
    }
}
