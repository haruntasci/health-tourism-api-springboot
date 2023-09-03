package com.allianz.healthtourism.service;

import com.allianz.healthtourism.database.entity.Appointment;
import com.allianz.healthtourism.database.entity.Hotel;
import com.allianz.healthtourism.database.entity.HotelBooking;
import com.allianz.healthtourism.database.repository.AppointmentRepository;
import com.allianz.healthtourism.database.repository.HotelBookingRepository;
import com.allianz.healthtourism.database.repository.HotelRepository;
import com.allianz.healthtourism.database.specification.HotelBookingSpecification;
import com.allianz.healthtourism.exception.CityIsNotSuitableException;
import com.allianz.healthtourism.exception.TimeIsNotSuitableException;
import com.allianz.healthtourism.mapper.HotelBookingMapper;
import com.allianz.healthtourism.model.HotelBookingDTO;
import com.allianz.healthtourism.model.requestDTO.HotelBookingRequestDTO;
import com.allianz.healthtourism.scheduler.HotelBookingScheduler;
import com.allianz.healthtourism.util.service.BaseService;
import com.allianz.healthtourism.util.constants.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
public class HotelBookingService extends BaseService<HotelBooking, HotelBookingDTO, HotelBookingRequestDTO,
        HotelBookingRepository, HotelBookingMapper, HotelBookingSpecification> {

    private final AppointmentRepository appointmentRepository;
    private final HotelRepository hotelRepository;
    private final HotelBookingRepository hotelBookingRepository;
    private final HotelBookingMapper hotelBookingMapper;
    private final HotelBookingScheduler hotelBookingScheduler;

    public HotelBookingService(HotelBookingRepository repository, HotelBookingMapper mapper,
                               HotelBookingSpecification specification, AppointmentRepository appointmentRepository,
                               HotelRepository hotelRepository, HotelBookingRepository hotelBookingRepository,
                               HotelBookingMapper hotelBookingMapper, HotelBookingScheduler hotelBookingScheduler) {
        super(repository, mapper, specification);
        this.appointmentRepository = appointmentRepository;
        this.hotelRepository = hotelRepository;
        this.hotelBookingRepository = hotelBookingRepository;
        this.hotelBookingMapper = hotelBookingMapper;
        this.hotelBookingScheduler = hotelBookingScheduler;
    }

    @Override
    public HotelBookingDTO save(HotelBookingRequestDTO requestDTO) {
        HotelBooking hotelBooking = new HotelBooking();
        populateHotelBookingWithHotel(hotelBooking, requestDTO);
        HotelBooking savedHotelBooking = hotelBookingRepository.save(hotelBooking);
        hotelBookingScheduler.startEvaluation(savedHotelBooking.getUuid());
        return hotelBookingMapper.entityToDto(savedHotelBooking);
    }

    @Override
    public HotelBookingDTO update(UUID uuid, HotelBookingRequestDTO requestDTO) {
        HotelBooking hotelBooking = getHotelBookingByUUID(uuid);
        if (hotelBooking != null) {
            populateHotelBookingWithHotel(hotelBooking, requestDTO);
            hotelBookingRepository.save(hotelBooking);
            return hotelBookingMapper.entityToDto(hotelBooking);
        } else {
            return null;
        }
    }

    @Override
    public Boolean deleteByUUID(UUID uuid) {
        updateEmptyRoomsInHotel(uuid);
        return super.deleteByUUID(uuid);
    }

    private void updateEmptyRoomsInHotel(UUID uuid) {
        HotelBooking hotelBooking = getHotelBookingByUUID(uuid);
        if (hotelBooking != null && hotelBooking.isConfirmed()) {
            Hotel hotel = hotelBooking.getHotel();
            hotel.setEmptyRoomCount(hotel.getEmptyRoomCount() + 1);
            hotelRepository.save(hotel);
        }
    }

    private void populateHotelBookingWithHotel(HotelBooking hotelBooking, HotelBookingRequestDTO requestDTO) {
        Appointment appointment = appointmentRepository.findByUuid(requestDTO.getAppointmentUUID()).orElse(null);
        Hotel hotel = hotelRepository.findByUuid(requestDTO.getHotelUUID()).orElse(null);
        if (appointment != null && hotel != null) {
            if (checkTimeAvailability(appointment, hotel) && checkCitySuitable(appointment, hotel)) {
                hotelBooking.setAppointment(appointment);
                hotelBooking.setHotel(hotel);
            }
        }
        hotelBooking.setPaid(requestDTO.isPaid());
    }

    private HotelBooking getHotelBookingByUUID(UUID uuid) {
        return hotelBookingRepository.findByUuid(uuid).orElse(null);
    }

    private boolean checkTimeAvailability(Appointment appointment, Hotel hotel) {
        try {
            if (hotel.getCheckIn().compareTo(appointment.getAppointmentDateTime().minusDays(1)) > 0) {
                log.error("Check-in time is not suitable for the appointment.");
                throw new TimeIsNotSuitableException(Constants.CHECK_IN_TIME_ERROR_MESSAGE);
            } else if (hotel.getCheckOut().compareTo(appointment.getAppointmentDateTime().plusDays(1)) < 0) {
                log.error("Check-out time is not suitable for the appointment.");
                throw new TimeIsNotSuitableException(Constants.CHECK_OUT_TIME_ERROR_MESSAGE);
            } else {
                return true;
            }
        } catch (TimeIsNotSuitableException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean checkCitySuitable(Appointment appointment, Hotel hotel) {
        try {
            if (!appointment.getDoctor().getHospital().getCity().equals(hotel.getCity())) {
                log.error("Hotel city is not the same as the doctor's city");
                throw new CityIsNotSuitableException(Constants.HOTEL_CITY_ERROR_MESSAGE);
            } else {
                return true;
            }
        } catch (CityIsNotSuitableException e) {
            throw new RuntimeException(e);
        }
    }
}
