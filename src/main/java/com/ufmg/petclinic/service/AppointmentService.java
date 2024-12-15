package com.ufmg.petclinic.service;

import java.util.List;
import java.util.UUID;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufmg.petclinic.model.Appointment;
import com.ufmg.petclinic.repository.AppointmentRepository;

@Service
public class AppointmentService {
    private final AppointmentRepository repository;

    @Autowired
    public AppointmentService(AppointmentRepository repository) {
        this.repository = repository;
    }

    public Appointment createAppointment(Appointment appointment) {
        return repository.save(appointment);
    }

    public Optional<Appointment> getAppointmentById(UUID id) {
        return repository.findById(id);
    }

    public List<Appointment> getAllAppointments() {
        return repository.findAll();
    }

    public void deleteAppointment(UUID id) {
        repository.deleteById(id);
    }

    public Optional<Appointment> updateAppointment(UUID id, Appointment updatedAppointment) {
        return repository.update(id, updatedAppointment);
    }
}
