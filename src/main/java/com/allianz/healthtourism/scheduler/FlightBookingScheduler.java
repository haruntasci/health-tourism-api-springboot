package com.allianz.healthtourism.scheduler;

import com.allianz.healthtourism.database.entity.Appointment;
import com.allianz.healthtourism.database.entity.Flight;
import com.allianz.healthtourism.database.entity.FlightBooking;
import com.allianz.healthtourism.database.repository.FlightBookingRepository;
import com.allianz.healthtourism.database.repository.FlightRepository;
import com.allianz.healthtourism.util.constants.Constants;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


import java.util.UUID;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Component
public class FlightBookingScheduler {
    private final FlightBookingRepository flightBookingRepository;
    private final FlightRepository flightRepository;
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    public FlightBookingScheduler(FlightBookingRepository flightBookingRepository, FlightRepository flightRepository) {
        this.flightBookingRepository = flightBookingRepository;
        this.flightRepository = flightRepository;
    }

    public void startEvaluation(UUID bookingUuid) {
        System.out.println("Entered into start evaluation");
        if (!evaluateBookingIfValid(bookingUuid)) {
            scheduleEvaluation(bookingUuid, Constants.SCHEDULER_DELAY, TimeUnit.MINUTES);
        }
    }

    private boolean evaluateBookingIfValid(UUID bookingUuid) {
        FlightBooking booking = getBookingByUUID(bookingUuid);
        if (booking != null) {
            Flight departureFlight = booking.getDepartureFlight();
            Flight returnFlight = booking.getReturnFlight();
            Appointment appointment = booking.getAppointment();
            if (booking.isPaid() && departureFlight != null && returnFlight != null && appointment != null) {
                confirmBooking(booking, departureFlight, returnFlight);
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    @Transactional
    public void evaluateBooking(UUID bookingUuid) {
        FlightBooking booking = getBookingByUUID(bookingUuid);
        if (booking != null) {
            Flight departureFlight = booking.getDepartureFlight();
            Flight returnFlight = booking.getReturnFlight();
            if (booking.isPaid() && departureFlight != null && returnFlight != null) {
                confirmBooking(booking, departureFlight, returnFlight);
            } else {
                cancelBooking(booking);
            }
        }
    }

    private FlightBooking getBookingByUUID(UUID bookingUuid) {
        return flightBookingRepository.findByUuid(bookingUuid).orElse(null);
    }

    @Transactional
    public void confirmBooking(FlightBooking booking, Flight departureFlight, Flight returnFlight) {
        System.out.println("Booking confirmed: " + booking.getUuid());
        departureFlight.setEmptySeatCount(departureFlight.getEmptySeatCount() - 1);
        returnFlight.setEmptySeatCount(returnFlight.getEmptySeatCount() - 1);
        Flight savedDepartureFlight = flightRepository.save(departureFlight);
        Flight savedReturnFlight = flightRepository.save(returnFlight);
        booking.setDepartureFlight(savedDepartureFlight);
        booking.setReturnFlight(savedReturnFlight);
        booking.setConfirmed(true);
        flightBookingRepository.save(booking);
    }

    private void cancelBooking(FlightBooking booking) {
        System.out.println("Booking cancelled: " + booking.getUuid());
        flightBookingRepository.delete(booking);
    }

    private void scheduleEvaluation(UUID bookingUuid, long delay, TimeUnit timeUnit) {
        scheduler.schedule(() -> evaluateBooking(bookingUuid), delay, timeUnit);
    }
}
