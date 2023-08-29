package com.allianz.healthtourism.service;

import com.allianz.healthtourism.database.entity.Flight;
import com.allianz.healthtourism.database.entity.FlightBooking;
import com.allianz.healthtourism.database.repository.FlightBookingRepository;
import com.allianz.healthtourism.database.repository.FlightRepository;
import com.allianz.healthtourism.database.specification.FlightBookingSpecification;
import com.allianz.healthtourism.mapper.FlightBookingMapper;
import com.allianz.healthtourism.model.FlightBookingDTO;
import com.allianz.healthtourism.model.requestDTO.FlightBookingRequestDTO;
import com.allianz.healthtourism.scheduler.FlightBookingScheduler;
import com.allianz.healthtourism.util.BaseService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class FlightBookingService extends BaseService<FlightBooking, FlightBookingDTO, FlightBookingRequestDTO,
        FlightBookingRepository, FlightBookingMapper, FlightBookingSpecification> {

    private final FlightRepository flightRepository;
    private final FlightBookingRepository flightBookingRepository;
    private final FlightBookingMapper flightBookingMapper;
    private final FlightBookingScheduler flightBookingScheduler;

    public FlightBookingService(FlightBookingRepository repository, FlightBookingMapper mapper,
                                FlightBookingSpecification specification,
                                FlightRepository flightRepository, FlightBookingRepository flightBookingRepository,
                                FlightBookingMapper flightBookingMapper, FlightBookingScheduler flightBookingScheduler) {
        super(repository, mapper, specification);
        this.flightRepository = flightRepository;
        this.flightBookingRepository = flightBookingRepository;
        this.flightBookingMapper = flightBookingMapper;
        this.flightBookingScheduler = flightBookingScheduler;
    }

    @Override
    public FlightBookingDTO save(FlightBookingRequestDTO requestDTO) {
        FlightBooking flightBooking = new FlightBooking();
        Flight departureFlight = flightRepository.findByUuid(requestDTO.getDepartureFlightUUID()).orElse(null);
        Flight returnFlight = flightRepository.findByUuid(requestDTO.getReturnFlightUUID()).orElse(null);
        if (departureFlight != null) {
            flightBooking.setDepartureFlight(departureFlight);
        }
        if (returnFlight != null) {
            flightBooking.setReturnFlight(returnFlight);
        }
        flightBooking.setIsPaid(requestDTO.getIsPaid());
        FlightBooking savedBooking = flightBookingRepository.save(flightBooking);
        flightBookingScheduler.startEvaluation(savedBooking.getUuid());
        return flightBookingMapper.entityToDto(savedBooking);
    }

    @Override
    public FlightBookingDTO update(UUID uuid, FlightBookingRequestDTO requestDTO) {
        FlightBooking flightBooking = flightBookingRepository.findByUuid(uuid).orElse(null);
        if (flightBooking != null) {
            FlightBooking flightBookingToSave = flightBookingMapper.requestDtoToExistEntity(requestDTO, flightBooking);
            Flight departureFlight = flightRepository.findByUuid(requestDTO.getDepartureFlightUUID()).orElse(null);
            if (departureFlight != null) {
                flightBookingToSave.setDepartureFlight(departureFlight);
            }
            Flight returnFlight = flightRepository.findByUuid(requestDTO.getReturnFlightUUID()).orElse(null);
            if (returnFlight != null) {
                flightBookingToSave.setReturnFlight(returnFlight);
            }
            flightBookingRepository.save(flightBookingToSave);
            return flightBookingMapper.entityToDto(flightBookingToSave);
        } else {
            return null;
        }
    }

    @Override
    public Boolean deleteByUUID(UUID uuid) {
        FlightBooking flightBooking = flightBookingRepository.findByUuid(uuid).orElse(null);
        if (flightBooking != null) {
            Flight departureFlight = flightBooking.getDepartureFlight();
            Flight returnFlight = flightBooking.getReturnFlight();
            departureFlight.setEmptySeatCount(departureFlight.getEmptySeatCount() + 1);
            returnFlight.setEmptySeatCount(returnFlight.getEmptySeatCount() + 1);
            flightRepository.save(departureFlight);
            flightRepository.save(returnFlight);
        }
        if (flightBookingRepository.deleteByUuid(uuid) == 1) {
            return true;
        } else {
            return false;
        }
    }
}
