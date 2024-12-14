package com.ufmg.petclinic.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufmg.petclinic.model.Clinic;
import com.ufmg.petclinic.repository.ClinicRepository;

@Service
public class ClinicService {

    @Autowired
    private ClinicRepository clinicRepository;

    public Clinic createClinic(long cnpj, String name, String address) {
        Clinic clinic = new Clinic(cnpj, name, address);
        return clinicRepository.save(clinic);
    }

    public List<Clinic> getAllClinics() {
        List<Clinic> clinics = clinicRepository.findAll();
        return clinics != null ? clinics : new ArrayList<>();
    }

    public Optional<Clinic> getClinicById(UUID id) {
        return clinicRepository.findById(id);
    }

    public boolean deleteClinic(UUID id) {
        return clinicRepository.deleteById(id);
    }

    public Optional<Clinic> updateClinic(UUID id, Clinic updatedClinic) {
        return clinicRepository.update(id, updatedClinic);
    }
}

