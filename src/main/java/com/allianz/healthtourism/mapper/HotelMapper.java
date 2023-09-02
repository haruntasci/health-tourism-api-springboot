package com.allianz.healthtourism.mapper;

import com.allianz.healthtourism.database.entity.Hotel;
import com.allianz.healthtourism.model.HotelDTO;
import com.allianz.healthtourism.model.requestDTO.HotelRequestDTO;
import com.allianz.healthtourism.util.mapper.IBaseMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface HotelMapper extends IBaseMapper<Hotel, HotelDTO, HotelRequestDTO> {
}
