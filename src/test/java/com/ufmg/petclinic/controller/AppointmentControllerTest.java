package com.ufmg.petclinic.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.ufmg.petclinic.model.Appointment;
import com.ufmg.petclinic.service.AppointmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AppointmentController.class)
public class AppointmentControllerTest {
    private static final String URL = "/api/v1/appointment";

    @MockBean
    private AppointmentService appointmentService;

    @Autowired
    protected MockMvc mockMvc;

    protected ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

    }

    @Test
    @DisplayName("Should create Appointment")
    public void createAppointment() throws Exception {
        var appointment = new Appointment(UUID.randomUUID(), UUID.randomUUID(), LocalDateTime.now());

        when(appointmentService.createAppointment(appointment)).thenReturn(appointment);

        mockMvc.perform(post(URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(appointment))
        ).andExpect(status().isOk());
    }

    @Test
    @DisplayName("should get Appointment by Id")
    public void getAppointmentById() throws Exception {
        var appointment = new Appointment(UUID.randomUUID(), UUID.randomUUID(), LocalDateTime.now());

        when(appointmentService.getAppointmentById(appointment.getId())).thenReturn(Optional.of(appointment));

        mockMvc.perform(get(URL + "/" + appointment.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("should not get appointment when not found")
    public void getAppointmentByIdNotFound() throws Exception {
        UUID id = UUID.randomUUID();
        when(appointmentService.getAppointmentById(id)).thenReturn(Optional.empty());

        mockMvc.perform(get(URL + "/" + id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("should get all appointments")
    public void getAllAppointments() throws Exception {
        var appointment1 = new Appointment(UUID.randomUUID(), UUID.randomUUID(), LocalDateTime.now());
        var appointment2 = new Appointment(UUID.randomUUID(), UUID.randomUUID(), LocalDateTime.now());

        var appointments = List.of(appointment1, appointment2);

        when(appointmentService.getAllAppointments()).thenReturn(appointments);

        mockMvc.perform(get(URL)
        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk())
        .andExpect(jsonPath("$.size()").value(2));
    }

    @Test
    @DisplayName("Should delete appointment")
    public void deleteAppointment() throws Exception {
        UUID id = UUID.randomUUID();

        doNothing().when(appointmentService).deleteAppointment(id);

        mockMvc.perform(delete(URL + "/" + id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    @DisplayName("should update appointment")
    public void shouldUpdateAppointment() throws Exception {
        var id = UUID.randomUUID();
        var updateAppointment = new Appointment(UUID.randomUUID(), UUID.randomUUID(), LocalDateTime.now());

        when(appointmentService.updateAppointment(eq(id), any(Appointment.class)))
                .thenReturn(Optional.of(updateAppointment));

        mockMvc.perform(put(URL + "/" + id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updateAppointment))
                ).andExpect(status().isOk());
    }

    @Test
    @DisplayName("should not update appointment when does not exist")
    public void shouldNotUpdateAppointmentWhenDoesNotExist() throws Exception {
        var id = UUID.randomUUID();
        var updatedAppointment = new Appointment(id, UUID.randomUUID(), LocalDateTime.now());

        when(appointmentService.updateAppointment(id,updatedAppointment))
                .thenReturn(Optional.empty());

        mockMvc.perform(put(URL + "/" + id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedAppointment)))
                .andExpect(status().isNotFound());
    }
}
