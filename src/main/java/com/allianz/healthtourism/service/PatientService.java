package com.allianz.healthtourism.service;

import com.allianz.healthtourism.database.entity.City;
import com.allianz.healthtourism.database.entity.Patient;
import com.allianz.healthtourism.database.repository.PatientRepository;
import com.allianz.healthtourism.database.specification.PatientSpecification;
import com.allianz.healthtourism.mapper.AppointmentMapper;
import com.allianz.healthtourism.mapper.CityMapper;
import com.allianz.healthtourism.mapper.PatientMapper;
import com.allianz.healthtourism.model.AppointmentDTO;
import com.allianz.healthtourism.model.CityDTO;
import com.allianz.healthtourism.model.PatientDTO;
import com.allianz.healthtourism.model.requestDTO.PatientRequestDTO;
import com.allianz.healthtourism.util.BaseService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
public class PatientService extends BaseService<Patient, PatientDTO, PatientRequestDTO,
        PatientRepository, PatientMapper, PatientSpecification> {

    private final CityService cityService;
    private final CityMapper cityMapper;
    private final PatientRepository patientRepository;
    private final PatientMapper patientMapper;

    public PatientService(PatientRepository repository, PatientMapper mapper, PatientSpecification specification,
                          CityService cityService,
                          CityMapper cityMapper, PatientRepository patientRepository, PatientMapper patientMapper) {
        super(repository, mapper, specification);

        this.cityService = cityService;
        this.cityMapper = cityMapper;
        this.patientRepository = patientRepository;
        this.patientMapper = patientMapper;
    }

    @Override
    public PatientDTO save(PatientRequestDTO requestDTO) {
        Patient patient = patientMapper.requestDtoToEntity(requestDTO);
        CityDTO cityDTO = cityService.getByUUID(requestDTO.getCityUUID());
        patient.setCity(cityMapper.dtoToEntity(cityDTO));
        patientRepository.save(patient);
        return patientMapper.entityToDto(patient);
    }

    @Override
    public PatientDTO update(UUID uuid, PatientRequestDTO requestDTO) {
        Patient patient = patientRepository.findByUuid(uuid).orElse(null);
        if (patient != null) {
            Patient patientToSave = patientMapper.requestDtoToExistEntity(requestDTO, patient);
            CityDTO cityDTO = cityService.getByUUID(requestDTO.getCityUUID());
            patientToSave.setCity(cityMapper.dtoToEntity(cityDTO));
            patientRepository.save(patientToSave);
            return patientMapper.entityToDto(patientToSave);
        } else {
            return null;
        }
    }
}
