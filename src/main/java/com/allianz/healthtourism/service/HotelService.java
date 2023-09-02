package com.allianz.healthtourism.service;

import com.allianz.healthtourism.database.entity.City;
import com.allianz.healthtourism.database.entity.Hotel;
import com.allianz.healthtourism.database.repository.CityRepository;
import com.allianz.healthtourism.database.repository.HotelRepository;
import com.allianz.healthtourism.database.specification.HotelSpecification;
import com.allianz.healthtourism.mapper.HotelMapper;
import com.allianz.healthtourism.model.HotelDTO;
import com.allianz.healthtourism.model.requestDTO.HotelRequestDTO;
import com.allianz.healthtourism.util.base.BaseService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class HotelService extends BaseService<Hotel, HotelDTO, HotelRequestDTO, HotelRepository, HotelMapper,
        HotelSpecification> {

    private final HotelRepository hotelRepository;
    private final HotelMapper hotelMapper;
    private final CityRepository cityRepository;

    public HotelService(HotelRepository repository, HotelMapper mapper,
                        HotelSpecification specification, HotelRepository hotelRepository,
                        HotelMapper hotelMapper, CityRepository cityRepository) {
        super(repository, mapper, specification);
        this.hotelRepository = hotelRepository;
        this.hotelMapper = hotelMapper;
        this.cityRepository = cityRepository;
    }

    @Override
    public HotelDTO save(HotelRequestDTO requestDTO) {
        Hotel hotel = hotelMapper.requestDtoToEntity(requestDTO);
        populateHotelWithCityAndSave(hotel, requestDTO);
        return hotelMapper.entityToDto(hotel);
    }

    @Override
    public HotelDTO update(UUID uuid, HotelRequestDTO requestDTO) {
        Hotel hotel = hotelRepository.findByUuid(uuid).orElse(null);
        if (hotel != null) {
            hotel = hotelMapper.requestDtoToExistEntity(requestDTO, hotel);
            populateHotelWithCityAndSave(hotel, requestDTO);
            return hotelMapper.entityToDto(hotel);
        } else {
            return null;
        }
    }

    private void populateHotelWithCityAndSave(Hotel hotel, HotelRequestDTO requestDTO) {
        City city = cityRepository.findByUuid(requestDTO.getCityUUID()).orElse(null);
        if (city != null) {
            hotel.setCity(city);
        }
        hotelRepository.save(hotel);
    }
}
