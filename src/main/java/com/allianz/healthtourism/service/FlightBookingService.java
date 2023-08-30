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
        populateFlightBookingWithObjects(flightBooking, requestDTO);
        FlightBooking savedBooking = flightBookingRepository.save(flightBooking);
        flightBookingScheduler.startEvaluation(savedBooking.getUuid());
        return flightBookingMapper.entityToDto(savedBooking);
    }

    @Override
    public FlightBookingDTO update(UUID uuid, FlightBookingRequestDTO requestDTO) {
        FlightBooking flightBooking = getFlightBookingByUUID(uuid);
        if (flightBooking != null) {
            populateFlightBookingWithObjects(flightBooking, requestDTO);
            flightBookingRepository.save(flightBooking);
            return flightBookingMapper.entityToDto(flightBooking);
        } else {
            return null;
        }
    }

    @Override
    public Boolean deleteByUUID(UUID uuid) {
        updateEmptySeatCountsInFlights(uuid);
        return super.deleteByUUID(uuid);
    }

    private void populateFlightBookingWithObjects(FlightBooking booking, FlightBookingRequestDTO requestDTO) {
        Flight departureFlight = flightRepository.findByUuid(requestDTO.getDepartureFlightUUID()).orElse(null);
        if (departureFlight != null) {
            booking.setDepartureFlight(departureFlight);
        }
        Flight returnFlight = flightRepository.findByUuid(requestDTO.getReturnFlightUUID()).orElse(null);
        if (returnFlight != null) {
            booking.setReturnFlight(returnFlight);
        }
        booking.setPaid(requestDTO.isPaid());
    }

    private FlightBooking getFlightBookingByUUID(UUID uuid) {
        return flightBookingRepository.findByUuid(uuid).orElse(null);
    }

    private void updateEmptySeatCountsInFlights(UUID uuid) {
        FlightBooking flightBooking = getFlightBookingByUUID(uuid);
        if (flightBooking != null && flightBooking.isConfirmed()) {
            Flight departureFlight = flightBooking.getDepartureFlight();
            Flight returnFlight = flightBooking.getReturnFlight();
            departureFlight.setEmptySeatCount(departureFlight.getEmptySeatCount() + 1);
            returnFlight.setEmptySeatCount(returnFlight.getEmptySeatCount() + 1);
            flightRepository.save(departureFlight);
            flightRepository.save(returnFlight);
        }
    }
}
