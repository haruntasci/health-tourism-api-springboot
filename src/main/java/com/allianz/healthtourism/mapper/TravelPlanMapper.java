package com.allianz.healthtourism.mapper;

import com.allianz.healthtourism.database.entity.TravelPlan;
import com.allianz.healthtourism.model.TravelPlanDTO;
import com.allianz.healthtourism.model.requestDTO.TravelPlanRequestDTO;
import com.allianz.healthtourism.util.IBaseMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TravelPlanMapper extends IBaseMapper<TravelPlan, TravelPlanDTO, TravelPlanRequestDTO> {
}
