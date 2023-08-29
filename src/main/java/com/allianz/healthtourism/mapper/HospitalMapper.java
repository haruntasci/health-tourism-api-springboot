package com.allianz.healthtourism.mapper;

import com.allianz.healthtourism.database.entity.Hospital;
import com.allianz.healthtourism.model.HospitalDTO;
import com.allianz.healthtourism.model.requestDTO.HospitalRequestDTO;
import com.allianz.healthtourism.util.IBaseMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface HospitalMapper extends IBaseMapper<Hospital, HospitalDTO, HospitalRequestDTO> {
}
