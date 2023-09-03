package com.allianz.healthtourism.controller;

import com.allianz.healthtourism.database.entity.MedicalRecord;
import com.allianz.healthtourism.database.repository.MedicalRecordRepository;
import com.allianz.healthtourism.database.specification.MedicalRecordSpecification;
import com.allianz.healthtourism.mapper.MedicalRecordMapper;
import com.allianz.healthtourism.model.MedicalRecordDTO;
import com.allianz.healthtourism.model.requestDTO.MedicalRecordRequestDTO;
import com.allianz.healthtourism.service.MedicalRecordService;
import com.allianz.healthtourism.util.controller.BaseController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("medical-record")
public class MedicalRecordController extends BaseController<MedicalRecord, MedicalRecordDTO, MedicalRecordRequestDTO,
        MedicalRecordMapper, MedicalRecordRepository, MedicalRecordSpecification, MedicalRecordService> {
    public MedicalRecordController(MedicalRecordService service) {
        super(service);
    }
}
