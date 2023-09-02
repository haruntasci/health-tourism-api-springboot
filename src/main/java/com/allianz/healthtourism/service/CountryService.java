package com.allianz.healthtourism.service;

import com.allianz.healthtourism.database.entity.Country;
import com.allianz.healthtourism.database.repository.CountryRepository;
import com.allianz.healthtourism.database.specification.CountrySpecification;
import com.allianz.healthtourism.mapper.CountryMapper;
import com.allianz.healthtourism.model.CountryDTO;
import com.allianz.healthtourism.model.requestDTO.CountryRequestDTO;
import com.allianz.healthtourism.util.base.BaseService;
import org.springframework.stereotype.Service;

@Service
public class CountryService extends BaseService<Country, CountryDTO,
        CountryRequestDTO, CountryRepository, CountryMapper, CountrySpecification> {
    public CountryService(CountryRepository repository, CountryMapper mapper, CountrySpecification specification) {
        super(repository, mapper, specification);
    }
}
