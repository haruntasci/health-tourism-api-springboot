package com.allianz.healthtourism.mapper;

import com.allianz.healthtourism.database.entity.City;
import com.allianz.healthtourism.model.CityDTO;
import com.allianz.healthtourism.model.requestDTO.CityRequestDTO;
import com.allianz.healthtourism.util.mapper.IBaseMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CityMapper extends IBaseMapper<City, CityDTO, CityRequestDTO> {
}
