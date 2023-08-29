package com.allianz.healthtourism.service;

import com.allianz.healthtourism.database.entity.City;
import com.allianz.healthtourism.database.entity.Hospital;
import com.allianz.healthtourism.database.repository.HospitalRepository;
import com.allianz.healthtourism.database.specification.HospitalSpecification;
import com.allianz.healthtourism.database.specification.HotelSpecification;
import com.allianz.healthtourism.mapper.CityMapper;
import com.allianz.healthtourism.mapper.HospitalMapper;
import com.allianz.healthtourism.model.CityDTO;
import com.allianz.healthtourism.model.HospitalDTO;
import com.allianz.healthtourism.model.requestDTO.HospitalRequestDTO;
import com.allianz.healthtourism.util.BaseService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class HospitalService extends BaseService<Hospital, HospitalDTO, HospitalRequestDTO, HospitalRepository,
        HospitalMapper, HospitalSpecification> {

    private final HospitalRepository hospitalRepository;
    private final HospitalMapper hospitalMapper;
    private final CityService cityService;
    private final CityMapper cityMapper;

    public HospitalService(HospitalRepository repository, HospitalMapper mapper, HospitalSpecification specification,
                           HospitalRepository hospitalRepository, HospitalMapper hospitalMapper,
                           CityService cityService, CityMapper cityMapper) {
        super(repository, mapper, specification);
        this.hospitalRepository = hospitalRepository;
        this.hospitalMapper = hospitalMapper;
        this.cityService = cityService;
        this.cityMapper = cityMapper;
    }

    @Override
    public HospitalDTO save(HospitalRequestDTO requestDTO) {
        Hospital hospital = new Hospital();
        hospital.setName(requestDTO.getName());
        CityDTO cityDTO = cityService.getByUUID(requestDTO.getCityUUID());
        hospital.setCity(cityMapper.dtoToEntity(cityDTO));
        hospitalRepository.save(hospital);
        return hospitalMapper.entityToDto(hospital);
    }

    @Override
    public HospitalDTO update(UUID uuid, HospitalRequestDTO requestDTO) {
        Hospital hospital = hospitalRepository.findByUuid(uuid).orElse(null);
        if (hospital != null) {
            CityDTO cityDTO = cityService.getByUUID(requestDTO.getCityUUID());
            hospital.setCity(cityMapper.dtoToEntity(cityDTO));
            hospital.setName(requestDTO.getName());
            hospitalRepository.save(hospital);
            return hospitalMapper.entityToDto(hospital);
        } else {
            return null;
        }
    }
}
