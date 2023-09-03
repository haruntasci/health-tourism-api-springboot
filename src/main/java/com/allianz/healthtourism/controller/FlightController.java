package com.allianz.healthtourism.controller;

import com.allianz.healthtourism.database.entity.Flight;
import com.allianz.healthtourism.database.repository.FlightRepository;
import com.allianz.healthtourism.database.specification.FlightSpecification;
import com.allianz.healthtourism.mapper.FlightMapper;
import com.allianz.healthtourism.model.FlightDTO;
import com.allianz.healthtourism.model.requestDTO.FlightRequestDTO;
import com.allianz.healthtourism.service.FlightService;
import com.allianz.healthtourism.util.controller.BaseController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/flight")
public class FlightController extends BaseController<
        Flight,
        FlightDTO,
        FlightRequestDTO,
        FlightMapper,
        FlightRepository,
        FlightSpecification,
        FlightService> {
    public FlightController(FlightService service) {
        super(service);
    }
}
