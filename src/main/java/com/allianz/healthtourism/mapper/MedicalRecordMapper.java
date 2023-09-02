package com.allianz.healthtourism.mapper;

import com.allianz.healthtourism.database.entity.MedicalRecord;
import com.allianz.healthtourism.model.MedicalRecordDTO;
import com.allianz.healthtourism.model.requestDTO.MedicalRecordRequestDTO;
import com.allianz.healthtourism.util.mapper.IBaseMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MedicalRecordMapper extends IBaseMapper<MedicalRecord, MedicalRecordDTO, MedicalRecordRequestDTO> {
}
