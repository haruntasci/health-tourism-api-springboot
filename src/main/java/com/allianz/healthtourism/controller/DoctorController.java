package com.allianz.healthtourism.controller;

import com.allianz.healthtourism.database.entity.Doctor;
import com.allianz.healthtourism.database.repository.DoctorRepository;
import com.allianz.healthtourism.database.specification.DoctorSpecification;
import com.allianz.healthtourism.mapper.DoctorMapper;
import com.allianz.healthtourism.model.DoctorDTO;
import com.allianz.healthtourism.model.requestDTO.DoctorRequestDTO;
import com.allianz.healthtourism.service.DoctorService;
import com.allianz.healthtourism.util.BaseController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("doctor")
public class DoctorController extends BaseController<Doctor, DoctorDTO, DoctorRequestDTO, DoctorMapper, DoctorRepository,
        DoctorSpecification, DoctorService> {
    public DoctorController(DoctorService service) {
        super(service);
    }
}
