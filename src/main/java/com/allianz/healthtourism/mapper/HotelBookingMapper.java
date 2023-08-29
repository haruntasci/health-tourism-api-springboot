package com.allianz.healthtourism.mapper;

import com.allianz.healthtourism.database.entity.HotelBooking;
import com.allianz.healthtourism.model.HotelBookingDTO;
import com.allianz.healthtourism.model.requestDTO.HotelBookingRequestDTO;
import com.allianz.healthtourism.util.IBaseMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface HotelBookingMapper extends IBaseMapper<HotelBooking, HotelBookingDTO, HotelBookingRequestDTO> {
}
