package com.ufmg.petclinic.controller;

import lombok.AllArgsConstructor;

import java.util.List;
import java.util.UUID;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.ufmg.petclinic.model.Clinic;
import com.ufmg.petclinic.service.ClinicService;

@RequestMapping("api/v1/clinic")
@RestController
@AllArgsConstructor
public class ClinicController {

    @Autowired
    private ClinicService clinicService;

    @PostMapping(path ="create")
    public ResponseEntity<Clinic> createClinic(@Validated @RequestBody Clinic clinic) {
        clinicService.createClinic(clinic);
        return new ResponseEntity<>(clinic, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Clinic>> getAllClinics() {
        List<Clinic> clinics = clinicService.getAllClinics();
        return new ResponseEntity<>(clinics, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Clinic> getClinicById(@PathVariable UUID id) {
        Optional<Clinic> clinic = clinicService.getClinicById(id);
        return clinic.map(ResponseEntity::ok)
                     .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClinic(@PathVariable UUID id) {
        boolean isDeleted = clinicService.deleteClinic(id);
        return isDeleted ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Clinic> updateClinic(@PathVariable UUID id, @RequestBody Clinic updatedClinic) {
        Optional<Clinic> clinic = clinicService.updateClinic(id, updatedClinic);
        return clinic.map(ResponseEntity::ok)
                     .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

   
}
