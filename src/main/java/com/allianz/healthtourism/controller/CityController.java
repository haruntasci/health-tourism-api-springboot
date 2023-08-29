package com.allianz.healthtourism.controller;

import com.allianz.healthtourism.database.entity.City;
import com.allianz.healthtourism.database.repository.CityRepository;
import com.allianz.healthtourism.database.specification.CitySpecification;
import com.allianz.healthtourism.mapper.CityMapper;
import com.allianz.healthtourism.model.CityDTO;
import com.allianz.healthtourism.model.requestDTO.CityRequestDTO;
import com.allianz.healthtourism.service.CityService;
import com.allianz.healthtourism.util.BaseController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("city")
public class CityController extends BaseController<City, CityDTO, CityRequestDTO, CityMapper, CityRepository,
        CitySpecification, CityService> {
    public CityController(CityService service) {
        super(service);
    }
}
