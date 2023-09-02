package com.allianz.healthtourism.mapper;

import com.allianz.healthtourism.database.entity.FlightBooking;
import com.allianz.healthtourism.model.FlightBookingDTO;
import com.allianz.healthtourism.model.requestDTO.FlightBookingRequestDTO;
import com.allianz.healthtourism.util.mapper.IBaseMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FlightBookingMapper extends IBaseMapper<FlightBooking, FlightBookingDTO, FlightBookingRequestDTO> {
}
