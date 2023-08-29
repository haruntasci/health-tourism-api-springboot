package com.allianz.healthtourism.mapper;

import com.allianz.healthtourism.database.entity.Appointment;
import com.allianz.healthtourism.model.AppointmentDTO;
import com.allianz.healthtourism.model.requestDTO.AppointmentRequestDTO;
import com.allianz.healthtourism.util.IBaseMapper;
import org.mapstruct.Mapper;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface AppointmentMapper extends IBaseMapper<Appointment, AppointmentDTO, AppointmentRequestDTO> {
}
