package com.allianz.healthtourism.service;

import com.allianz.healthtourism.database.entity.Appointment;
import com.allianz.healthtourism.database.entity.Doctor;
import com.allianz.healthtourism.database.entity.MedicalRecord;
import com.allianz.healthtourism.database.entity.Patient;
import com.allianz.healthtourism.database.repository.AppointmentRepository;
import com.allianz.healthtourism.database.repository.DoctorRepository;
import com.allianz.healthtourism.database.repository.MedicalRecordRepository;
import com.allianz.healthtourism.database.repository.PatientRepository;
import com.allianz.healthtourism.database.specification.AppointmentSpecification;
import com.allianz.healthtourism.mapper.AppointmentMapper;
import com.allianz.healthtourism.model.AppointmentDTO;
import com.allianz.healthtourism.model.requestDTO.AppointmentDateRequestDTO;
import com.allianz.healthtourism.model.requestDTO.AppointmentRequestDTO;
import com.allianz.healthtourism.model.requestDTO.AppointmentMedicalRecordDTO;
import com.allianz.healthtourism.scheduler.AppointmentScheduler;
import com.allianz.healthtourism.util.base.BaseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service

public class AppointmentService extends BaseService<Appointment, AppointmentDTO, AppointmentRequestDTO, AppointmentRepository,
        AppointmentMapper, AppointmentSpecification> {

    private final AppointmentRepository appointmentRepository;
    private final AppointmentMapper appointmentMapper;
    private final AppointmentScheduler appointmentScheduler;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;
    private final MedicalRecordRepository medicalRecordRepository;


    public AppointmentService(AppointmentRepository repository, AppointmentMapper mapper,
                              AppointmentSpecification specification, AppointmentRepository appointmentRepository,
                              AppointmentMapper appointmentMapper, AppointmentScheduler appointmentScheduler,
                              DoctorRepository doctorRepository, PatientRepository patientRepository,
                              MedicalRecordRepository medicalRecordRepository) {
        super(repository, mapper, specification);
        this.appointmentRepository = appointmentRepository;
        this.appointmentMapper = appointmentMapper;
        this.appointmentScheduler = appointmentScheduler;
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;

        this.medicalRecordRepository = medicalRecordRepository;
    }

    @Override
    public AppointmentDTO save(AppointmentRequestDTO requestDTO) {
        Appointment appointment = new Appointment();
        populateAppointmentWithObjects(appointment, requestDTO);
        Appointment savedAppointment = appointmentRepository.save(appointment);
        appointmentScheduler.startEvaluation(savedAppointment.getUuid());
        return appointmentMapper.entityToDto(appointment);
    }

    @Override
    public AppointmentDTO update(UUID uuid, AppointmentRequestDTO requestDTO) {
        Appointment appointment = appointmentRepository.findByUuid(uuid).orElse(null);
        if (appointment != null) {
            populateAppointmentWithObjects(appointment, requestDTO);
            appointmentRepository.save(appointment);
            return appointmentMapper.entityToDto(appointment);
        } else {
            return null;
        }
    }

    @Transactional
    public AppointmentDTO addMedicalRecordToAppointment(UUID appointmentUUID,
                                                        AppointmentMedicalRecordDTO medicalRecordDTO) {
        Appointment appointment = getAppointmentByUUID(appointmentUUID);
        if (appointment != null) {
            MedicalRecord medicalRecord = medicalRecordRepository.findByUuid(medicalRecordDTO
                    .getMedicalRecordUUID()).orElse(null);
            if (medicalRecord != null) {
                appointment.setMedicalRecord(medicalRecord);
            }
            appointmentRepository.save(appointment);
            return appointmentMapper.entityToDto(appointment);
        } else {
            return null;
        }
    }

    @Transactional
    public AppointmentDTO updateAppointmentDate(UUID appointmentUUID, AppointmentDateRequestDTO dateRequest) {
        Appointment appointment = getAppointmentByUUID(appointmentUUID);
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

    private void populateAppointmentWithObjects(Appointment appointment, AppointmentRequestDTO requestDTO) {
        Doctor doctor = doctorRepository.findByUuid(requestDTO.getDoctorUUID()).orElse(null);
        if (doctor != null) {
            appointment.setDoctor(doctor);
        }
        Patient patient = patientRepository.findByUuid(requestDTO.getPatientUUID()).orElse(null);
        if (patient != null) {
            appointment.setPatient(patient);
        }
        if (requestDTO.getAppointmentDateTime() != null) {
            appointment.setAppointmentDateTime(requestDTO.getAppointmentDateTime());
        }
        appointment.setPaid(requestDTO.isPaid());
    }

    private Appointment getAppointmentByUUID(UUID uuid) {
        return appointmentRepository.findByUuid(uuid).orElse(null);
    }
}
