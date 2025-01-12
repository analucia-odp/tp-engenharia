package com.ufmg.petclinic.repository;

import com.ufmg.petclinic.model.Appointment;

import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class AppointmentRepository {
    private final List<Appointment> appointments = new ArrayList<>();

    public Appointment save(Appointment appointment) {
        if (appointment.getId() == null) {
            appointment.setId(UUID.randomUUID());
        }
        appointments.add(appointment);
        return appointment;
    }

    public Optional<Appointment> findById(UUID id) {
        return appointments.stream().filter(a -> a.getId().equals(id)).findFirst();
    }

    public List<Appointment> findAll() {
        return new ArrayList<>(appointments);
    }

    public void deleteById(UUID id) {
        appointments.removeIf(a -> a.getId().equals(id));
    }

    public Optional<Appointment> update(UUID id, Appointment updatedAppointment) {
        return findById(id).map(existingAppointment -> {
            existingAppointment.setClinicId(updatedAppointment.getClinicId());
            existingAppointment.setUserId(updatedAppointment.getUserId());
            existingAppointment.setAppointmentDateTime(updatedAppointment.getAppointmentDateTime());
            return existingAppointment;
        });
    }

     public List<LocalDateTime> findUnavailableTimes(UUID clinicId, LocalDateTime startDate, LocalDateTime endDate) {
        return appointments.stream()
                .filter(a -> a.getClinicId().equals(clinicId))
                .filter(a -> !a.getAppointmentDateTime().isBefore(startDate) &&
                             !a.getAppointmentDateTime().isAfter(endDate))
                .map(Appointment::getAppointmentDateTime)
                .collect(Collectors.toList());
    }
}

