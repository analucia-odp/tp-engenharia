package com.ufmg.petclinic.service;

import com.ufmg.petclinic.repository.AppointmentRepository;
import com.ufmg.petclinic.model.Appointment;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class AppointmentServiceTest {

    @Mock
    private AppointmentRepository repository;

    private AppointmentService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        service = new AppointmentService(repository);
    }

    @Test
    void shouldCreateAppointment() {
        Appointment appointment = new Appointment(UUID.randomUUID(), UUID.randomUUID(), LocalDateTime.now());
        when(repository.save(any(Appointment.class))).thenReturn(appointment);

        Appointment created = service.createAppointment(appointment);

        assertNotNull(created);
        verify(repository, times(1)).save(appointment);
    }

    @Test
    void shouldGetAppointmentById() {
        Appointment appointment = new Appointment(UUID.randomUUID(), UUID.randomUUID(), LocalDateTime.now());
        UUID id = appointment.getId();
        when(repository.findById(id)).thenReturn(Optional.of(appointment));

        Optional<Appointment> found = service.getAppointmentById(id);

        assertTrue(found.isPresent());
        assertEquals(id, found.get().getId());
        verify(repository, times(1)).findById(id);
    }

    @Test
    void shouldReturnEmptyIfAppointmentNotFound() {
        UUID id = UUID.randomUUID();
        when(repository.findById(id)).thenReturn(Optional.empty());

        Optional<Appointment> found = service.getAppointmentById(id);

        assertFalse(found.isPresent());
    }

    @Test
    void shouldDeleteAppointment() {
        UUID id = UUID.randomUUID();
        doNothing().when(repository).deleteById(id);

        service.deleteAppointment(id);

        verify(repository, times(1)).deleteById(id);
    }
}
