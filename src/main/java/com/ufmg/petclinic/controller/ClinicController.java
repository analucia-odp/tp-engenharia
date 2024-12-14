package com.ufmg.petclinic.controller;

import lombok.AllArgsConstructor;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Clinic> createClinic(@RequestParam long cnpj, @RequestParam String name, @RequestParam String address) {
        Clinic clinic = clinicService.createClinic(cnpj, name, address);
        return new ResponseEntity<>(clinic, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Clinic>> getAllClinics() {
        List<Clinic> clinics = clinicService.getAllClinics();
        return new ResponseEntity<>(clinics, HttpStatus.OK);
    }

}
