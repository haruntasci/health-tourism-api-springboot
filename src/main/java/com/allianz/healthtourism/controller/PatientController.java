package com.allianz.healthtourism.controller;

import com.allianz.healthtourism.database.entity.Patient;
import com.allianz.healthtourism.database.repository.PatientRepository;
import com.allianz.healthtourism.database.specification.PatientSpecification;
import com.allianz.healthtourism.mapper.PatientMapper;
import com.allianz.healthtourism.model.PatientDTO;
import com.allianz.healthtourism.model.requestDTO.PatientRequestDTO;
import com.allianz.healthtourism.service.PatientService;
import com.allianz.healthtourism.util.base.BaseController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("patient")
public class PatientController extends BaseController<Patient, PatientDTO, PatientRequestDTO, PatientMapper, PatientRepository,
        PatientSpecification, PatientService> {
    public PatientController(PatientService service) {
        super(service);
    }
}
