package com.allianz.healthtourism.mapper;

import com.allianz.healthtourism.database.entity.Doctor;
import com.allianz.healthtourism.model.DoctorDTO;
import com.allianz.healthtourism.model.requestDTO.DoctorRequestDTO;
import com.allianz.healthtourism.util.mapper.IBaseMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DoctorMapper extends IBaseMapper<Doctor, DoctorDTO, DoctorRequestDTO> {
}
