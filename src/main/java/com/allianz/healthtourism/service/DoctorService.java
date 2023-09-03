package com.allianz.healthtourism.service;

import com.allianz.healthtourism.database.entity.Doctor;
import com.allianz.healthtourism.database.entity.Hospital;
import com.allianz.healthtourism.database.repository.DoctorRepository;
import com.allianz.healthtourism.database.repository.HospitalRepository;
import com.allianz.healthtourism.database.specification.DoctorSpecification;
import com.allianz.healthtourism.mapper.DoctorMapper;
import com.allianz.healthtourism.model.DoctorDTO;
import com.allianz.healthtourism.model.requestDTO.DoctorRequestDTO;
import com.allianz.healthtourism.util.service.BaseService;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
public class DoctorService extends BaseService<Doctor, DoctorDTO, DoctorRequestDTO,
        DoctorRepository, DoctorMapper, DoctorSpecification> {

    private final DoctorRepository doctorRepository;
    private final DoctorMapper doctorMapper;
    private final HospitalRepository hospitalRepository;


    public DoctorService(DoctorRepository repository, DoctorMapper mapper,
                         DoctorSpecification specification, DoctorRepository doctorRepository,
                         DoctorMapper doctorMapper, HospitalRepository hospitalRepository) {
        super(repository, mapper, specification);
        this.doctorRepository = doctorRepository;
        this.doctorMapper = doctorMapper;
        this.hospitalRepository = hospitalRepository;
    }

    @Override
    public DoctorDTO save(DoctorRequestDTO requestDTO) {
        Doctor doctor = doctorMapper.requestDtoToEntity(requestDTO);
        populateDoctorWithHospitalAndSave(doctor, requestDTO);
        return doctorMapper.entityToDto(doctor);
    }

    @Override
    public DoctorDTO update(UUID uuid, DoctorRequestDTO requestDTO) {
        Doctor doctor = doctorRepository.findByUuid(uuid).orElse(null);
        if (doctor != null) {
            doctor = doctorMapper.requestDtoToExistEntity(requestDTO, doctor);
            populateDoctorWithHospitalAndSave(doctor, requestDTO);
            return doctorMapper.entityToDto(doctor);
        } else {
            return null;
        }
    }

    private void populateDoctorWithHospitalAndSave(Doctor doctor, DoctorRequestDTO requestDTO) {
        Hospital hospital = hospitalRepository.findByUuid(requestDTO.getHospitalUUID()).orElse(null);
        if (hospital != null) {
            doctor.setHospital(hospital);
        }
        doctorRepository.save(doctor);
    }
}
