package com.allianz.healthtourism.mapper;

import com.allianz.healthtourism.database.entity.Flight;
import com.allianz.healthtourism.model.FlightDTO;
import com.allianz.healthtourism.model.requestDTO.FlightRequestDTO;
import com.allianz.healthtourism.util.mapper.IBaseMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FlightMapper extends IBaseMapper<Flight, FlightDTO, FlightRequestDTO> {
}
