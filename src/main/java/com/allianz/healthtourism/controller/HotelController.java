package com.allianz.healthtourism.controller;

import com.allianz.healthtourism.database.entity.Hotel;
import com.allianz.healthtourism.database.repository.HotelRepository;
import com.allianz.healthtourism.database.specification.HotelSpecification;
import com.allianz.healthtourism.mapper.HotelMapper;
import com.allianz.healthtourism.model.HotelDTO;
import com.allianz.healthtourism.model.requestDTO.HotelRequestDTO;
import com.allianz.healthtourism.service.HotelService;
import com.allianz.healthtourism.util.BaseController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hotel")
public class HotelController extends BaseController<Hotel, HotelDTO, HotelRequestDTO, HotelMapper, HotelRepository,
        HotelSpecification, HotelService> {
    public HotelController(HotelService service) {
        super(service);
    }
}
