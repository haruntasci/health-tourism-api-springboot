package com.allianz.healthtourism.service;

import com.allianz.healthtourism.database.entity.Hotel;
import com.allianz.healthtourism.database.entity.HotelBooking;
import com.allianz.healthtourism.database.repository.HotelBookingRepository;
import com.allianz.healthtourism.database.repository.HotelRepository;
import com.allianz.healthtourism.database.specification.HotelBookingSpecification;
import com.allianz.healthtourism.database.specification.HotelSpecification;
import com.allianz.healthtourism.mapper.HotelBookingMapper;
import com.allianz.healthtourism.model.HotelBookingDTO;
import com.allianz.healthtourism.model.requestDTO.HotelBookingRequestDTO;
import com.allianz.healthtourism.scheduler.HotelBookingScheduler;
import com.allianz.healthtourism.util.BaseService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class HotelBookingService extends BaseService<HotelBooking, HotelBookingDTO, HotelBookingRequestDTO,
        HotelBookingRepository, HotelBookingMapper, HotelBookingSpecification> {

    private final HotelRepository hotelRepository;
    private final HotelBookingRepository hotelBookingRepository;
    private final HotelBookingMapper hotelBookingMapper;
    private final HotelBookingScheduler hotelBookingScheduler;

    public HotelBookingService(HotelBookingRepository repository, HotelBookingMapper mapper,
                               HotelBookingSpecification specification,
                               HotelRepository hotelRepository, HotelBookingRepository hotelBookingRepository,
                               HotelBookingMapper hotelBookingMapper, HotelBookingScheduler hotelBookingScheduler) {
        super(repository, mapper, specification);
        this.hotelRepository = hotelRepository;
        this.hotelBookingRepository = hotelBookingRepository;
        this.hotelBookingMapper = hotelBookingMapper;
        this.hotelBookingScheduler = hotelBookingScheduler;
    }

    @Override
    public HotelBookingDTO save(HotelBookingRequestDTO requestDTO) {
        HotelBooking hotelBooking = new HotelBooking();
        Hotel hotel = hotelRepository.findByUuid(requestDTO.getHotelUUID()).orElse(null);
        if (hotel != null) {
            hotelBooking.setHotel(hotel);
        }
        hotelBooking.setIsPaid(requestDTO.getIsPaid());
        HotelBooking savedHotelBooking = hotelBookingRepository.save(hotelBooking);
        hotelBookingScheduler.startEvaluation(savedHotelBooking.getUuid());
        return hotelBookingMapper.entityToDto(savedHotelBooking);
    }

    @Override
    public HotelBookingDTO update(UUID uuid, HotelBookingRequestDTO requestDTO) {
        HotelBooking hotelBooking = hotelBookingRepository.findByUuid(uuid).orElse(null);
        if (hotelBooking != null) {
            HotelBooking hotelBookingToSave = hotelBookingMapper.requestDtoToExistEntity(requestDTO, hotelBooking);
            Hotel hotel = hotelRepository.findByUuid(requestDTO.getHotelUUID()).orElse(null);
            if (hotel != null) {
                hotelBookingToSave.setHotel(hotel);
            }
            hotelBookingRepository.save(hotelBookingToSave);
            return hotelBookingMapper.entityToDto(hotelBookingToSave);
        } else {
            return null;
        }
    }

    @Override
    public Boolean deleteByUUID(UUID uuid) {
        HotelBooking hotelBooking = hotelBookingRepository.findByUuid(uuid).orElse(null);
        if (hotelBooking != null) {
            Hotel hotel = hotelBooking.getHotel();
            hotel.setEmptyRoomCount(hotel.getEmptyRoomCount() + 1);
            hotelRepository.save(hotel);
        }
        if (hotelBookingRepository.deleteByUuid(uuid) == 1) {
            return true;
        } else {
            return false;
        }
    }
}
