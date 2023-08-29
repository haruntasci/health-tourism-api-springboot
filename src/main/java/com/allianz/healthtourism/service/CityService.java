package com.allianz.healthtourism.service;

import com.allianz.healthtourism.database.entity.City;
import com.allianz.healthtourism.database.entity.Country;
import com.allianz.healthtourism.database.repository.CityRepository;
import com.allianz.healthtourism.database.specification.CitySpecification;
import com.allianz.healthtourism.mapper.CityMapper;
import com.allianz.healthtourism.mapper.CountryMapper;
import com.allianz.healthtourism.model.CityDTO;
import com.allianz.healthtourism.model.CountryDTO;
import com.allianz.healthtourism.model.requestDTO.CityRequestDTO;
import com.allianz.healthtourism.util.BaseService;
import org.springframework.stereotype.Service;

@Service
public class CityService extends BaseService<City, CityDTO, CityRequestDTO, CityRepository, CityMapper, CitySpecification> {
    private final CityRepository cityRepository;
    private final CityMapper cityMapper;
    private final CountryService countryService;
    private final CountryMapper countryMapper;

    public CityService(CityRepository repository, CityMapper mapper,CitySpecification specification,
                       CityRepository cityRepository, CityMapper cityMapper,
                       CountryService countryService, CountryMapper countryMapper) {
        super(repository, mapper, specification);
        this.cityRepository = cityRepository;
        this.cityMapper = cityMapper;
        this.countryService = countryService;
        this.countryMapper = countryMapper;
    }

    @Override
    public CityDTO save(CityRequestDTO requestDTO) {
        City city = new City();
        city.setName(requestDTO.getName());
        CountryDTO countryDTO = countryService.getByUUID(requestDTO.getCountryUUID());
        city.setCountry(countryMapper.dtoToEntity(countryDTO));
        cityRepository.save(city);
        return cityMapper.entityToDto(city);
    }
}
