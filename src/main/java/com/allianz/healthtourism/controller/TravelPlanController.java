package com.allianz.healthtourism.controller;

import com.allianz.healthtourism.database.entity.TravelPlan;
import com.allianz.healthtourism.database.repository.TravelPlanRepository;
import com.allianz.healthtourism.database.specification.TravelPlanSpecification;
import com.allianz.healthtourism.mapper.TravelPlanMapper;
import com.allianz.healthtourism.model.TravelPlanDTO;
import com.allianz.healthtourism.model.requestDTO.TravelPlanRequestDTO;
import com.allianz.healthtourism.service.TravelPlanService;
import com.allianz.healthtourism.util.BaseController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("travel-plan")
public class TravelPlanController extends BaseController<TravelPlan, TravelPlanDTO, TravelPlanRequestDTO, TravelPlanMapper,
        TravelPlanRepository, TravelPlanSpecification, TravelPlanService> {
    public TravelPlanController(TravelPlanService service) {
        super(service);
    }
}
