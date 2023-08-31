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
        System.out.println("Entered into start evaluation");
        if (!evaluateBookingIfValid(bookingUuid)) {
            scheduleEvaluation(bookingUuid, 10, TimeUnit.MINUTES);
        }
    }

    private boolean evaluateBookingIfValid(UUID bookingUuid) {
        HotelBooking booking = getBookingByUUID(bookingUuid);
        if (booking != null) {
            Hotel hotel = booking.getHotel();
            if (booking.isPaid() && hotel != null) {
                confirmBooking(booking, hotel);
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
        HotelBooking booking = getBookingByUUID(bookingUuid);
        if (booking != null) {
            Hotel hotel = booking.getHotel();
            if (booking.isPaid() && hotel != null) {
                confirmBooking(booking, hotel);
            } else {
                cancelBooking(booking);
            }
        }
    }

    @Transactional
    public void confirmBooking(HotelBooking booking, Hotel hotel) {
        System.out.println("Booking confirmed: " + booking.getUuid());
        hotel.setEmptyRoomCount(hotel.getEmptyRoomCount() - 1);
        Hotel savedHotel = hotelRepository.save(hotel);
        booking.setHotel(savedHotel);
        booking.setConfirmed(true);
        hotelBookingRepository.save(booking);
    }

    private void cancelBooking(HotelBooking booking) {
        System.out.println("Booking cancelled: " + booking.getUuid());
        hotelBookingRepository.delete(booking);
    }

    private HotelBooking getBookingByUUID(UUID bookingUuid) {
        return hotelBookingRepository.findByUuid(bookingUuid).orElse(null);
    }

    private void scheduleEvaluation(UUID bookingUuid, long delay, TimeUnit timeUnit) {
        scheduler.schedule(() -> evaluateBooking(bookingUuid), delay, timeUnit);
    }


}
