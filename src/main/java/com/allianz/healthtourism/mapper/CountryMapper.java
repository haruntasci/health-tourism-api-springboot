package com.allianz.healthtourism.mapper;

import com.allianz.healthtourism.database.entity.Country;
import com.allianz.healthtourism.model.CountryDTO;
import com.allianz.healthtourism.model.requestDTO.CountryRequestDTO;
import com.allianz.healthtourism.util.mapper.IBaseMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CountryMapper extends IBaseMapper<Country, CountryDTO, CountryRequestDTO> {
}
