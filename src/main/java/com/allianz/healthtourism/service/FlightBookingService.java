package com.allianz.healthtourism.service;

import com.allianz.healthtourism.database.entity.Appointment;
import com.allianz.healthtourism.database.entity.Flight;
import com.allianz.healthtourism.database.entity.FlightBooking;
import com.allianz.healthtourism.database.repository.AppointmentRepository;
import com.allianz.healthtourism.database.repository.FlightBookingRepository;
import com.allianz.healthtourism.database.repository.FlightRepository;
import com.allianz.healthtourism.database.specification.FlightBookingSpecification;
import com.allianz.healthtourism.exception.CityIsNotSuitableException;
import com.allianz.healthtourism.exception.TimeIsNotSuitableException;
import com.allianz.healthtourism.mapper.FlightBookingMapper;
import com.allianz.healthtourism.model.FlightBookingDTO;
import com.allianz.healthtourism.model.requestDTO.FlightBookingRequestDTO;
import com.allianz.healthtourism.scheduler.FlightBookingScheduler;
import com.allianz.healthtourism.util.service.BaseService;
import com.allianz.healthtourism.util.constants.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Slf4j
public class FlightBookingService extends BaseService<FlightBooking, FlightBookingDTO, FlightBookingRequestDTO,
        FlightBookingRepository, FlightBookingMapper, FlightBookingSpecification> {

    private final AppointmentRepository appointmentRepository;
    private final FlightRepository flightRepository;
    private final FlightBookingRepository flightBookingRepository;
    private final FlightBookingMapper flightBookingMapper;
    private final FlightBookingScheduler flightBookingScheduler;

    public FlightBookingService(FlightBookingRepository repository, FlightBookingMapper mapper,
                                FlightBookingSpecification specification, AppointmentRepository appointmentRepository,
                                FlightRepository flightRepository, FlightBookingRepository flightBookingRepository,
                                FlightBookingMapper flightBookingMapper, FlightBookingScheduler flightBookingScheduler) {
        super(repository, mapper, specification);
        this.appointmentRepository = appointmentRepository;
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

        Appointment appointment = appointmentRepository.findByUuid(requestDTO.getAppointmentUUID()).orElse(null);
        Flight departureFlight = flightRepository.findByUuid(requestDTO.getDepartureFlightUUID()).orElse(null);
        Flight returnFlight = flightRepository.findByUuid(requestDTO.getReturnFlightUUID()).orElse(null);

        if (appointment != null && departureFlight != null && returnFlight != null) {
            if (checkTimeAvailability(appointment, departureFlight, returnFlight) &&
                    checkCitiesSuitable(appointment, departureFlight, returnFlight)) {
                booking.setAppointment(appointment);
                booking.setDepartureFlight(departureFlight);
                booking.setReturnFlight(returnFlight);
            }
        }
        booking.setPaid(requestDTO.isPaid());
    }

    private FlightBooking getFlightBookingByUUID(UUID uuid) {
        return flightBookingRepository.findByUuid(uuid).orElse(null);
    }

    @Transactional
    public void updateEmptySeatCountsInFlights(UUID uuid) {
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

    private boolean checkTimeAvailability(Appointment appointment, Flight departureFlight, Flight returnFlight) {
        try {
            if (departureFlight.getArrivalTime().compareTo(appointment.getAppointmentDateTime().minusHours(4)) > 0) {
                log.error(Constants.DEPARTURE_FLIGHT_TIME_ERROR_MESSAGE);
                throw new TimeIsNotSuitableException(Constants.DEPARTURE_FLIGHT_TIME_ERROR_MESSAGE);
            } else if (returnFlight.getDepartureTime().compareTo(appointment.getAppointmentDateTime().plusHours(4)) < 0) {
                log.error(Constants.RETURN_FLIGHT_TIME_ERROR_MESSAGE);
                throw new TimeIsNotSuitableException(Constants.RETURN_FLIGHT_TIME_ERROR_MESSAGE);
            } else {
                return true;
            }
        } catch (TimeIsNotSuitableException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean checkCitiesSuitable(Appointment appointment, Flight departureFlight, Flight returnFlight) {
        try {
            if (!appointment.getPatient().getCity().equals(departureFlight.getDepartureCity())) {
                log.error(Constants.DEPARTURE_FLIGHT_DEPARTURE_CITY_ERROR_MESSAGE);
                throw new CityIsNotSuitableException(Constants.DEPARTURE_FLIGHT_DEPARTURE_CITY_ERROR_MESSAGE);
            } else if (!appointment.getDoctor().getHospital().getCity().equals(departureFlight.getArrivalCity())) {
                log.error(Constants.DEPARTURE_FLIGHT_ARRIVAL_CITY_ERROR_MESSAGE);
                throw new CityIsNotSuitableException(Constants.DEPARTURE_FLIGHT_ARRIVAL_CITY_ERROR_MESSAGE);
            } else if (!appointment.getPatient().getCity().equals(returnFlight.getArrivalCity())) {
                log.error(Constants.RETURN_FLIGHT_ARRIVAL_CITY_ERROR_MESSAGE);
                throw new CityIsNotSuitableException(Constants.RETURN_FLIGHT_ARRIVAL_CITY_ERROR_MESSAGE);
            } else if (!appointment.getDoctor().getHospital().getCity().equals(returnFlight.getDepartureCity())) {
                log.error(Constants.RETURN_FLIGHT_DEPARTURE_CITY_ERROR_MESSAGE);
                throw new CityIsNotSuitableException(Constants.RETURN_FLIGHT_DEPARTURE_CITY_ERROR_MESSAGE);
            } else {
                return true;
            }
        } catch (CityIsNotSuitableException e) {
            throw new RuntimeException(e);
        }
    }
}
