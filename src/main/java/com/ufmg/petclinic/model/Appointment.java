package com.ufmg.petclinic.model;

import java.time.LocalDateTime;
import java.util.UUID;


public class Appointment {
    private UUID id;
    private UUID userId;
    private UUID clinicId;
    private LocalDateTime appointmentDateTime;

    public Appointment(UUID id, UUID userId, UUID clinicId, LocalDateTime appointmentDateTime) {
        this.id = id;
        this.userId = userId;
        this.clinicId = clinicId;
        this.appointmentDateTime = appointmentDateTime;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public UUID getClinicId() {
        return clinicId;
    }

    public void setClinicId(UUID clinicId) {
        this.clinicId = clinicId;
    }

    public LocalDateTime getAppointmentDateTime() {
        return appointmentDateTime;
    }

    public void setAppointmentDateTime(LocalDateTime appointmentDateTime) {
        this.appointmentDateTime = appointmentDateTime;
    }
}
