package com.ufmg.petclinic.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.Optional;
import java.util.stream.Stream;

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

    public boolean isTimeAvailable(UUID clinicId, LocalDateTime appointmentDateTime) {
        LocalDateTime startOfDay = appointmentDateTime.toLocalDate().atTime(8, 00);
        LocalDateTime endOfDay = appointmentDateTime.toLocalDate().atTime(18, 00);

        List<LocalDateTime> unavailableTimes = repository.findUnavailableTimes(clinicId, startOfDay, endOfDay);
    
        return !unavailableTimes.contains(appointmentDateTime);
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

    public List<LocalDateTime> getAvailableTimes(UUID clinicId, LocalDateTime startDate, LocalDateTime endDate) {
        List<LocalDateTime> unavailableTimes = repository.findUnavailableTimes(clinicId, startDate, endDate);

        // Supondo que os horários disponíveis sigam um padrao de intervalos de 1 hora entre startDate e endDate
        List<LocalDateTime> allPossibleTimes = Stream.iterate(startDate, time -> time.plusHours(1))
                .limit(startDate.until(endDate, java.time.temporal.ChronoUnit.HOURS) + 1)
                .collect(Collectors.toList());

        return allPossibleTimes.stream()
                .filter(time -> !unavailableTimes.contains(time))
                .collect(Collectors.toList());
    }
}
