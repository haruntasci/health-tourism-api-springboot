package com.allianz.healthtourism.scheduler;

import com.allianz.healthtourism.database.entity.Flight;
import com.allianz.healthtourism.database.entity.FlightBooking;
import com.allianz.healthtourism.database.entity.Hotel;
import com.allianz.healthtourism.database.entity.HotelBooking;
import com.allianz.healthtourism.database.repository.HotelBookingRepository;
import com.allianz.healthtourism.database.repository.HotelRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Component
public class HotelBookingScheduler {
    private final HotelBookingRepository hotelBookingRepository;
    private final HotelRepository hotelRepository;
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    public HotelBookingScheduler(HotelBookingRepository hotelBookingRepository, HotelRepository hotelRepository) {
        this.hotelBookingRepository = hotelBookingRepository;
        this.hotelRepository = hotelRepository;
    }

    public void startEvaluation(UUID bookingUuid) {
        scheduler.schedule(() -> evaluateBooking(bookingUuid), 30, TimeUnit.SECONDS);
        System.out.println("Entered into start evaluation");

    }

    @Transactional
    public void evaluateBooking(UUID bookingUuid) {
        HotelBooking booking = hotelBookingRepository.findByUuid(bookingUuid).orElse(null);
        if (booking != null) {
            Hotel hotel = booking.getHotel();
            if (booking.getIsPaid() && hotel != null) {
                System.out.println("Booking confirmed: " + booking.getUuid());
                hotel.setEmptyRoomCount(hotel.getEmptyRoomCount() - 1);
                Hotel savedHotel = hotelRepository.save(hotel);
                booking.setHotel(savedHotel);
                booking.setIsConfirmed(true);
                hotelBookingRepository.save(booking);
            } else {
                System.out.println("Booking cancelled: " + booking.getUuid());
                hotelBookingRepository.delete(booking);
            }
        }
    }


}
