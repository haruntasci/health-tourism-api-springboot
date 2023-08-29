package com.allianz.healthtourism.service;

import com.allianz.healthtourism.database.entity.MedicalRecord;
import com.allianz.healthtourism.database.repository.MedicalRecordRepository;
import com.allianz.healthtourism.database.specification.MedicalRecordSpecification;
import com.allianz.healthtourism.mapper.MedicalRecordMapper;
import com.allianz.healthtourism.model.MedicalRecordDTO;
import com.allianz.healthtourism.model.requestDTO.MedicalRecordRequestDTO;
import com.allianz.healthtourism.util.BaseService;
import org.springframework.stereotype.Service;

@Service
public class MedicalRecordService extends BaseService<MedicalRecord, MedicalRecordDTO, MedicalRecordRequestDTO,
        MedicalRecordRepository, MedicalRecordMapper, MedicalRecordSpecification> {
    public MedicalRecordService(MedicalRecordRepository repository, MedicalRecordMapper mapper,
                                MedicalRecordSpecification specification) {
        super(repository, mapper, specification);
    }
}
