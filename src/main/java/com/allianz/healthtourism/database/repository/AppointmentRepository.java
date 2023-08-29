package com.allianz.healthtourism.database.repository;

import com.allianz.healthtourism.database.entity.Appointment;
import com.allianz.healthtourism.database.entity.Patient;
import com.allianz.healthtourism.util.IBaseRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepository extends IBaseRepository<Appointment> {
    List<Appointment> findAppointmentsByPatientIdentityNumber(String patientIdentity);
}
