package com.allianz.healthtourism.mapper;

import com.allianz.healthtourism.database.entity.Patient;
import com.allianz.healthtourism.model.PatientDTO;
import com.allianz.healthtourism.model.requestDTO.PatientRequestDTO;
import com.allianz.healthtourism.util.mapper.IBaseMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PatientMapper extends IBaseMapper<Patient, PatientDTO, PatientRequestDTO> {
}
