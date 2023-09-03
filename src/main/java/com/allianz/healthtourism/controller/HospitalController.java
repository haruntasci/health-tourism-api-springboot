package com.allianz.healthtourism.controller;

import com.allianz.healthtourism.database.entity.Hospital;
import com.allianz.healthtourism.database.repository.HospitalRepository;
import com.allianz.healthtourism.database.specification.HospitalSpecification;
import com.allianz.healthtourism.mapper.HospitalMapper;
import com.allianz.healthtourism.model.HospitalDTO;
import com.allianz.healthtourism.model.requestDTO.HospitalRequestDTO;
import com.allianz.healthtourism.service.HospitalService;
import com.allianz.healthtourism.util.controller.BaseController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("hospital")
public class HospitalController extends BaseController<Hospital, HospitalDTO, HospitalRequestDTO, HospitalMapper,
        HospitalRepository, HospitalSpecification, HospitalService> {
    public HospitalController(HospitalService service) {
        super(service);
    }
}
