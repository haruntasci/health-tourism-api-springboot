package com.allianz.healthtourism.controller;

import com.allianz.healthtourism.database.entity.Country;
import com.allianz.healthtourism.database.repository.CountryRepository;
import com.allianz.healthtourism.database.specification.CountrySpecification;
import com.allianz.healthtourism.mapper.CountryMapper;
import com.allianz.healthtourism.model.CountryDTO;
import com.allianz.healthtourism.model.requestDTO.CountryRequestDTO;
import com.allianz.healthtourism.service.CountryService;
import com.allianz.healthtourism.util.controller.BaseController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("country")
public class CountryController extends BaseController<Country, CountryDTO, CountryRequestDTO, CountryMapper, CountryRepository,
        CountrySpecification, CountryService> {
    public CountryController(CountryService service) {
        super(service);
    }
}
