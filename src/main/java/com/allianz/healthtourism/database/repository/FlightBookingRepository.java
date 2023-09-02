package com.allianz.healthtourism.database.repository;

import com.allianz.healthtourism.database.entity.FlightBooking;
import com.allianz.healthtourism.util.repository.IBaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface FlightBookingRepository  extends IBaseRepository<FlightBooking> {
    @Query("SELECT fb FROM FlightBooking fb WHERE fb.isPaid = false AND fb.creationDate < :twoMinutesAgo")
    List<FlightBooking> findIncompleteBookings(@Param("twoMinutesAgo") LocalDateTime tenMinutesAgo);
}
