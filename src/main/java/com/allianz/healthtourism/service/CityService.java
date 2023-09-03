package com.allianz.healthtourism.service;

import com.allianz.healthtourism.database.entity.City;
import com.allianz.healthtourism.database.entity.Country;
import com.allianz.healthtourism.database.repository.CityRepository;
import com.allianz.healthtourism.database.repository.CountryRepository;
import com.allianz.healthtourism.database.specification.CitySpecification;
import com.allianz.healthtourism.mapper.CityMapper;
import com.allianz.healthtourism.mapper.CountryMapper;
import com.allianz.healthtourism.model.CityDTO;
import com.allianz.healthtourism.model.requestDTO.CityRequestDTO;
import com.allianz.healthtourism.util.service.BaseService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CityService extends BaseService<City, CityDTO, CityRequestDTO, CityRepository, CityMapper, CitySpecification> {
    private final CityRepository cityRepository;
    private final CityMapper cityMapper;
    private final CountryRepository countryRepository;

    public CityService(CityRepository repository, CityMapper mapper, CitySpecification specification,
                       CityRepository cityRepository, CityMapper cityMapper,
                       CountryService countryService, CountryMapper countryMapper, CountryRepository countryRepository) {
        super(repository, mapper, specification);
        this.cityRepository = cityRepository;
        this.cityMapper = cityMapper;
        this.countryRepository = countryRepository;
    }

    @Override
    public CityDTO save(CityRequestDTO requestDTO) {
        City city = cityMapper.requestDtoToEntity(requestDTO);
        populateCityWithCountryAndSave(city, requestDTO);
        return cityMapper.entityToDto(city);
    }

    @Override
    public CityDTO update(UUID uuid, CityRequestDTO requestDTO) {
        City city = cityRepository.findByUuid(uuid).orElse(null);
        if (city != null) {
            city = cityMapper.requestDtoToExistEntity(requestDTO, city);
            populateCityWithCountryAndSave(city, requestDTO);
            return cityMapper.entityToDto(city);
        } else {
            return null;
        }
    }

    private void populateCityWithCountryAndSave(City city, CityRequestDTO requestDTO) {
        Country country = countryRepository.findByUuid(requestDTO.getCountryUUID()).orElse(null);
        if (country != null) {
            city.setCountry(country);
        }
        cityRepository.save(city);
    }
}
