package com.allianz.healthtourism.mapper;

import com.allianz.healthtourism.database.entity.Appointment;
import com.allianz.healthtourism.model.AppointmentDTO;
import com.allianz.healthtourism.model.requestDTO.AppointmentRequestDTO;
import com.allianz.healthtourism.util.mapper.IBaseMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AppointmentMapper extends IBaseMapper<Appointment, AppointmentDTO, AppointmentRequestDTO> {
}
