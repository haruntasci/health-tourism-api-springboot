package com.allianz.healthtourism.controller;

import com.allianz.healthtourism.database.entity.Appointment;
import com.allianz.healthtourism.database.repository.AppointmentRepository;
import com.allianz.healthtourism.database.specification.AppointmentSpecification;
import com.allianz.healthtourism.mapper.AppointmentMapper;
import com.allianz.healthtourism.model.AppointmentDTO;
import com.allianz.healthtourism.model.requestDTO.AppointmentDateRequestDTO;
import com.allianz.healthtourism.model.requestDTO.AppointmentRequestDTO;
import com.allianz.healthtourism.model.requestDTO.AppointmentMedicalRecordDTO;
import com.allianz.healthtourism.service.AppointmentService;
import com.allianz.healthtourism.util.controller.BaseController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("appointment")
public class AppointmentController extends BaseController<Appointment, AppointmentDTO, AppointmentRequestDTO,
        AppointmentMapper, AppointmentRepository, AppointmentSpecification, AppointmentService> {
    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService service, AppointmentService appointmentService) {
        super(service);
        this.appointmentService = appointmentService;
    }


    @PutMapping("/add-medical-record/{uuid}")
    public ResponseEntity<AppointmentDTO> addMedicalRecordToAppointment(@PathVariable UUID uuid,
                                                                        @RequestBody AppointmentMedicalRecordDTO medicalDTO

    ) {
        return new ResponseEntity<>(appointmentService.addMedicalRecordToAppointment(uuid, medicalDTO), HttpStatus.OK);
    }

    @PutMapping("/update-appointment-date/{uuid}")
    public ResponseEntity<AppointmentDTO> updateAppointmentDate(@PathVariable UUID uuid,
                                                                @RequestBody AppointmentDateRequestDTO dateRequestDTO) {
        return new ResponseEntity<>(appointmentService.updateAppointmentDate(uuid, dateRequestDTO), HttpStatus.OK);
    }

    @GetMapping("/get-patients-appointments/{identityNumber}")
    public ResponseEntity<List<AppointmentDTO>> getAppointmentsByPatientIdentityNumber(@PathVariable String identityNumber) {
        return new ResponseEntity<>(appointmentService.getAppointmentsByPatientIdentity(identityNumber), HttpStatus.OK);
    }


}
