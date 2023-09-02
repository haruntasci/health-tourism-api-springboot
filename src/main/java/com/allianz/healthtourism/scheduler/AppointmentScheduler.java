package com.allianz.healthtourism.scheduler;

import com.allianz.healthtourism.database.entity.*;
import com.allianz.healthtourism.database.repository.*;
import com.allianz.healthtourism.util.constants.Constants;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Component
public class AppointmentScheduler {
    private final AppointmentRepository appointmentRepository;
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    public AppointmentScheduler(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }


    public void startEvaluation(UUID appointmentUuid) {
        System.out.println("Entered into start evaluation");
        if (!evaluateBookingIfValid(appointmentUuid)) {
            scheduleEvaluation(appointmentUuid, Constants.SCHEDULER_DELAY, TimeUnit.MINUTES);
        }
    }

    private boolean evaluateBookingIfValid(UUID appointmentUuid) {
        Appointment appointment = getAppointmentByUUID(appointmentUuid);
        if (appointment != null) {

            if (appointment.getDoctor() != null && appointment.getPatient() != null &&
                    appointment.isPaid() && appointment.getAppointmentDateTime() != null) {
                confirmAppointment(appointment);
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    @Transactional
    public void evaluateBooking(UUID appointmentUuid) {
        Appointment appointment = getAppointmentByUUID(appointmentUuid);
        if (appointment != null) {
            if (appointment.getDoctor() != null && appointment.getPatient() != null &&
                    appointment.isPaid() && appointment.getAppointmentDateTime() != null) {
                confirmAppointment(appointment);
            } else {
                cancelAppointment(appointment);
            }
        }
    }

    @Transactional
    public void confirmAppointment(Appointment appointment) {
        System.out.println("Appointment confirmed: " + appointment.getUuid());
        appointment.setConfirmed(true);
        appointmentRepository.save(appointment);
    }

    private void cancelAppointment(Appointment appointment) {
        System.out.println("Appointment cancelled: " + appointment.getUuid());
        appointmentRepository.delete(appointment);
    }

    private Appointment getAppointmentByUUID(UUID appointmentUuid) {
        return appointmentRepository.findByUuid(appointmentUuid).orElse(null);
    }

    private void scheduleEvaluation(UUID appointmentUuid, long delay, TimeUnit timeUnit) {
        scheduler.schedule(() -> evaluateBooking(appointmentUuid), delay, timeUnit);
    }

}
