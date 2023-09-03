package com.allianz.healthtourism.controller;

import com.allianz.healthtourism.database.entity.FlightBooking;
import com.allianz.healthtourism.database.repository.FlightBookingRepository;
import com.allianz.healthtourism.database.specification.FlightBookingSpecification;
import com.allianz.healthtourism.mapper.FlightBookingMapper;
import com.allianz.healthtourism.model.FlightBookingDTO;
import com.allianz.healthtourism.model.requestDTO.FlightBookingRequestDTO;
import com.allianz.healthtourism.service.FlightBookingService;
import com.allianz.healthtourism.util.controller.BaseController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/flight-booking")
public class FlightBookingController extends BaseController<FlightBooking, FlightBookingDTO, FlightBookingRequestDTO,
        FlightBookingMapper, FlightBookingRepository, FlightBookingSpecification, FlightBookingService> {
    public FlightBookingController(FlightBookingService service) {
        super(service);
    }
}
