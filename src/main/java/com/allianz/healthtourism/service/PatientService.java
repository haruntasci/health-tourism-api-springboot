package com.allianz.healthtourism.service;

import com.allianz.healthtourism.database.entity.City;
import com.allianz.healthtourism.database.entity.Patient;
import com.allianz.healthtourism.database.repository.CityRepository;
import com.allianz.healthtourism.database.repository.PatientRepository;
import com.allianz.healthtourism.database.specification.PatientSpecification;
import com.allianz.healthtourism.mapper.PatientMapper;
import com.allianz.healthtourism.model.CityDTO;
import com.allianz.healthtourism.model.PatientDTO;
import com.allianz.healthtourism.model.requestDTO.PatientRequestDTO;
import com.allianz.healthtourism.util.BaseService;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
public class PatientService extends BaseService<Patient, PatientDTO, PatientRequestDTO,
        PatientRepository, PatientMapper, PatientSpecification> {

    private final CityRepository cityRepository;
    private final PatientRepository patientRepository;
    private final PatientMapper patientMapper;

    public PatientService(PatientRepository repository, PatientMapper mapper,
                          PatientSpecification specification, CityRepository cityRepository,
                          PatientRepository patientRepository, PatientMapper patientMapper) {
        super(repository, mapper, specification);
        this.cityRepository = cityRepository;
        this.patientRepository = patientRepository;
        this.patientMapper = patientMapper;
    }

    @Override
    public PatientDTO save(PatientRequestDTO requestDTO) {
        Patient patient = patientMapper.requestDtoToEntity(requestDTO);
        populatePatientWithCityAndSave(patient, requestDTO);
        return patientMapper.entityToDto(patient);
    }

    @Override
    public PatientDTO update(UUID uuid, PatientRequestDTO requestDTO) {
        Patient patient = patientRepository.findByUuid(uuid).orElse(null);
        if (patient != null) {
            patient = patientMapper.requestDtoToExistEntity(requestDTO, patient);
            populatePatientWithCityAndSave(patient, requestDTO);
            return patientMapper.entityToDto(patient);
        } else {
            return null;
        }
    }

    private void populatePatientWithCityAndSave(Patient patient, PatientRequestDTO requestDTO) {
        City city = cityRepository.findByUuid(requestDTO.getCityUUID()).orElse(null);
        if (city != null) {
            patient.setCity(city);
        }
        patientRepository.save(patient);
    }
}
