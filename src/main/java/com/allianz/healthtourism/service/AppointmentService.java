package com.allianz.healthtourism.service;

import com.allianz.healthtourism.database.entity.Appointment;
import com.allianz.healthtourism.database.entity.MedicalRecord;
import com.allianz.healthtourism.database.repository.AppointmentRepository;
import com.allianz.healthtourism.database.specification.AppointmentSpecification;
import com.allianz.healthtourism.mapper.AppointmentMapper;
import com.allianz.healthtourism.mapper.DoctorMapper;
import com.allianz.healthtourism.mapper.MedicalRecordMapper;
import com.allianz.healthtourism.mapper.PatientMapper;
import com.allianz.healthtourism.model.AppointmentDTO;
import com.allianz.healthtourism.model.DoctorDTO;
import com.allianz.healthtourism.model.MedicalRecordDTO;
import com.allianz.healthtourism.model.PatientDTO;
import com.allianz.healthtourism.model.requestDTO.AppointmentDateRequestDTO;
import com.allianz.healthtourism.model.requestDTO.AppointmentRequestDTO;
import com.allianz.healthtourism.model.requestDTO.AppointmentMedicalRecordDTO;
import com.allianz.healthtourism.util.BaseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class AppointmentService extends BaseService<Appointment, AppointmentDTO, AppointmentRequestDTO, AppointmentRepository,
        AppointmentMapper, AppointmentSpecification> {

    private final AppointmentRepository appointmentRepository;
    private final AppointmentMapper appointmentMapper;
    private final DoctorService doctorService;
    private final DoctorMapper doctorMapper;
    private final PatientService patientService;
    private final PatientMapper patientMapper;
    private final MedicalRecordService medicalRecordService;
    private final MedicalRecordMapper medicalRecordMapper;

    public AppointmentService(AppointmentRepository repository, AppointmentMapper mapper, AppointmentSpecification specification,
                              AppointmentRepository appointmentRepository, AppointmentMapper appointmentMapper,
                              DoctorService doctorService, DoctorMapper doctorMapper,
                              PatientService patientService, PatientMapper patientMapper,
                              MedicalRecordService medicalRecordService, MedicalRecordMapper medicalRecordMapper) {
        super(repository, mapper, specification);
        this.appointmentRepository = appointmentRepository;
        this.appointmentMapper = appointmentMapper;
        this.doctorService = doctorService;
        this.doctorMapper = doctorMapper;
        this.patientService = patientService;
        this.patientMapper = patientMapper;
        this.medicalRecordService = medicalRecordService;
        this.medicalRecordMapper = medicalRecordMapper;
    }

    @Override
    public AppointmentDTO save(AppointmentRequestDTO requestDTO) {
        Appointment appointment = new Appointment();
        DoctorDTO doctorDTO = doctorService.getByUUID(requestDTO.getDoctorUUID());
        appointment.setDoctor(doctorMapper.dtoToEntity(doctorDTO));
        PatientDTO patientDTO = patientService.getByUUID(requestDTO.getPatientUUID());
        appointment.setPatient(patientMapper.dtoToEntity(patientDTO));
        if (requestDTO.getAppointmentDateTime() != null) {
            appointment.setAppointmentDateTime(requestDTO.getAppointmentDateTime());
        }

        appointmentRepository.save(appointment);
        return appointmentMapper.entityToDto(appointment);
    }

    @Override
    public AppointmentDTO update(UUID uuid, AppointmentRequestDTO requestDTO) {
        Appointment appointment = appointmentRepository.findByUuid(uuid).orElse(null);
        if (appointment != null) {
            if (requestDTO.getDoctorUUID() != null) {
                DoctorDTO doctorDTO = doctorService.getByUUID(requestDTO.getDoctorUUID());
                appointment.setDoctor(doctorMapper.dtoToEntity(doctorDTO));
            }
            if (requestDTO.getPatientUUID() != null) {
                PatientDTO patientDTO = patientService.getByUUID(requestDTO.getPatientUUID());
                appointment.setPatient(patientMapper.dtoToEntity(patientDTO));
            }
            if (requestDTO.getAppointmentDateTime() != null) {
                appointment.setAppointmentDateTime(requestDTO.getAppointmentDateTime());
            }
            appointmentRepository.save(appointment);
            return appointmentMapper.entityToDto(appointment);
        } else {
            return null;
        }
    }

    @Transactional
    public AppointmentDTO addMedicalRecordToAppointment(UUID appointmentUUID,
                                                        AppointmentMedicalRecordDTO medicalRecordDTO) {
        Appointment appointment = appointmentRepository.findByUuid(appointmentUUID).orElse(null);
        if (appointment != null) {
            MedicalRecordDTO medicalRecordToAdd = medicalRecordService.getByUUID(medicalRecordDTO.getMedicalRecordUUID());
            appointment.setMedicalRecord(medicalRecordMapper.dtoToEntity(medicalRecordToAdd));
            appointmentRepository.save(appointment);
            return appointmentMapper.entityToDto(appointment);
        } else {
            return null;
        }
    }

    @Transactional
    public AppointmentDTO updateAppointmentDate(UUID appointmentUUID, AppointmentDateRequestDTO dateRequest) {
        Appointment appointment = appointmentRepository.findByUuid(appointmentUUID).orElse(null);
        if (appointment != null) {
            appointment.setAppointmentDateTime(dateRequest.getAppointmentDateTime());
            appointmentRepository.save(appointment);
            return appointmentMapper.entityToDto(appointment);
        } else {
            return null;
        }
    }


    public List<AppointmentDTO> getAppointmentsByPatientIdentity(String identityNumber) {
        List<Appointment> appointments = appointmentRepository
                .findAppointmentsByPatientIdentityNumber(identityNumber);
        return appointmentMapper.entityListToDtoList(appointments);
    }
}
