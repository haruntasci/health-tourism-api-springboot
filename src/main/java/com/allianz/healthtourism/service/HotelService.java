package com.allianz.healthtourism.service;

import com.allianz.healthtourism.database.entity.City;
import com.allianz.healthtourism.database.entity.Hotel;
import com.allianz.healthtourism.database.repository.HotelRepository;
import com.allianz.healthtourism.database.specification.HotelSpecification;
import com.allianz.healthtourism.mapper.CityMapper;
import com.allianz.healthtourism.mapper.HotelMapper;
import com.allianz.healthtourism.model.CityDTO;
import com.allianz.healthtourism.model.HotelDTO;
import com.allianz.healthtourism.model.requestDTO.HotelRequestDTO;
import com.allianz.healthtourism.util.BaseService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class HotelService extends BaseService<Hotel, HotelDTO, HotelRequestDTO, HotelRepository, HotelMapper,
        HotelSpecification> {

    private final HotelRepository hotelRepository;
    private final HotelMapper hotelMapper;
    private final CityService cityService;
    private final CityMapper cityMapper;

    public HotelService(HotelRepository repository, HotelMapper mapper, HotelSpecification specification,
                        HotelRepository hotelRepository,
                        HotelMapper hotelMapper, CityService cityService, CityMapper cityMapper) {
        super(repository, mapper, specification);
        this.hotelRepository = hotelRepository;
        this.hotelMapper = hotelMapper;
        this.cityService = cityService;
        this.cityMapper = cityMapper;
    }

    @Override
    public HotelDTO save(HotelRequestDTO requestDTO) {
        Hotel hotel = new Hotel();
        CityDTO cityDTO = cityService.getByUUID(requestDTO.getCityUUID());
        hotel.setCity(cityMapper.dtoToEntity(cityDTO));
        hotel.setCheckIn(requestDTO.getCheckIn());
        hotel.setCheckOut(requestDTO.getCheckOut());
        hotel.setEmptyRoomCount(requestDTO.getEmptyRoomCount());
        hotel.setTotalRoomCount(requestDTO.getTotalRoomCount());
        hotelRepository.save(hotel);
        return hotelMapper.entityToDto(hotel);
    }

    @Override
    public HotelDTO update(UUID uuid, HotelRequestDTO requestDTO) {
        Hotel hotel = hotelRepository.findByUuid(uuid).orElse(null);
        if (hotel != null) {
            Hotel hotelToSave = hotelMapper.requestDtoToExistEntity(requestDTO, hotel);
            CityDTO cityDTO = cityService.getByUUID(requestDTO.getCityUUID());
            hotelToSave.setCity(cityMapper.dtoToEntity(cityDTO));
            hotelRepository.save(hotelToSave);
            return hotelMapper.entityToDto(hotelToSave);
        } else {
            return null;
        }
    }
}
