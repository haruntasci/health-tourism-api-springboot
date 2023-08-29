package com.allianz.healthtourism.service;

import com.allianz.healthtourism.database.entity.Doctor;
import com.allianz.healthtourism.database.entity.Hospital;
import com.allianz.healthtourism.database.repository.DoctorRepository;
import com.allianz.healthtourism.database.specification.DoctorSpecification;
import com.allianz.healthtourism.mapper.DoctorMapper;
import com.allianz.healthtourism.mapper.HospitalMapper;
import com.allianz.healthtourism.mapper.PatientMapper;
import com.allianz.healthtourism.model.DoctorDTO;
import com.allianz.healthtourism.model.HospitalDTO;
import com.allianz.healthtourism.model.requestDTO.DoctorRequestDTO;
import com.allianz.healthtourism.util.BaseService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
public class DoctorService extends BaseService<Doctor, DoctorDTO, DoctorRequestDTO,
        DoctorRepository, DoctorMapper, DoctorSpecification> {

    private final DoctorRepository doctorRepository;
    private final DoctorMapper doctorMapper;
    private final HospitalService hospitalService;
    private final HospitalMapper hospitalMapper;


    public DoctorService(DoctorRepository repository, DoctorMapper mapper, DoctorSpecification specification,
                         DoctorRepository doctorRepository,
                         DoctorMapper doctorMapper, HospitalService hospitalService, HospitalMapper hospitalMapper) {
        super(repository, mapper, specification);
        this.doctorRepository = doctorRepository;
        this.doctorMapper = doctorMapper;
        this.hospitalService = hospitalService;
        this.hospitalMapper = hospitalMapper;
    }

    @Override
    public DoctorDTO save(DoctorRequestDTO requestDTO) {
        Doctor doctor = doctorMapper.requestDtoToEntity(requestDTO);
        HospitalDTO hospitalDTO = hospitalService.getByUUID(requestDTO.getHospitalUUID());
        doctor.setHospital(hospitalMapper.dtoToEntity(hospitalDTO));
        doctorRepository.save(doctor);
        return doctorMapper.entityToDto(doctor);
    }

    @Override
    public DoctorDTO update(UUID uuid, DoctorRequestDTO requestDTO) {
        Doctor doctor = doctorRepository.findByUuid(uuid).orElse(null);
        if (doctor != null) {
            Doctor doctorToSave = doctorMapper.requestDtoToExistEntity(requestDTO, doctor);
            HospitalDTO hospitalDTO = hospitalService.getByUUID(requestDTO.getHospitalUUID());
            doctorToSave.setHospital(hospitalMapper.dtoToEntity(hospitalDTO));
            doctorRepository.save(doctorToSave);
            return doctorMapper.entityToDto(doctorToSave);
        } else {
            return null;
        }
    }
}
