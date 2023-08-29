package com.allianz.healthtourism.scheduler;

import com.allianz.healthtourism.database.entity.Flight;
import com.allianz.healthtourism.database.entity.FlightBooking;
import com.allianz.healthtourism.database.repository.FlightBookingRepository;
import com.allianz.healthtourism.database.repository.FlightRepository;
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
        scheduler.schedule(() -> evaluateBooking(bookingUuid), 30, TimeUnit.SECONDS);
        System.out.println("Entered into start evaluation");
    }

    @Transactional
    public void evaluateBooking(UUID bookingUuid) {
        FlightBooking booking = flightBookingRepository.findByUuid(bookingUuid).orElse(null);
        if (booking != null) {
            Flight departureFlight = booking.getDepartureFlight();
            Flight returnFlight = booking.getReturnFlight();
            if (booking.getIsPaid() && departureFlight != null && returnFlight != null) {
                System.out.println("Booking confirmed: " + booking.getUuid());
                departureFlight.setEmptySeatCount(departureFlight.getEmptySeatCount() - 1);
                returnFlight.setEmptySeatCount(returnFlight.getEmptySeatCount() - 1);
                Flight savedDepartureFlight = flightRepository.save(departureFlight);
                Flight savedReturnFlight = flightRepository.save(returnFlight);
                booking.setDepartureFlight(savedDepartureFlight);
                booking.setReturnFlight(savedReturnFlight);
                booking.setIsConfirmed(true);
                flightBookingRepository.save(booking);
            } else {
                System.out.println("Booking cancelled: " + booking.getUuid());
                flightBookingRepository.delete(booking);
            }
        }
    }
}
