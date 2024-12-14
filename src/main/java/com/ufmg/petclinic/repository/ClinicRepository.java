package com.ufmg.petclinic.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.ufmg.petclinic.model.Clinic;

@Repository
public class ClinicRepository {
    
    private List<Clinic> clinics = new ArrayList<>();


    public Clinic insertClinic(Clinic clinic) {
        Optional<Clinic> existingClinic = findByCnpj(clinic.getCnpj());
        if (existingClinic.isPresent()) {
            throw new IllegalArgumentException("Clinic with this CNPJ already exists");
        }
        clinics.add(clinic);
        return clinic;
    }


    public Optional<Clinic> findByCnpj(Long cnpj) {
        return clinics.stream()
                .filter(clinic -> clinic.getCnpj().equals(cnpj))
                .findFirst();
    }

   
    public List<Clinic> findAll() {
        return clinics;
    }


    public Optional<Clinic> update(Long cnpj, Clinic clinicDetails) {
        Optional<Clinic> clinicOpt = findByCnpj(cnpj);
        clinicOpt.ifPresent(clinic -> {
            clinic.setName(clinicDetails.getName());
            clinic.setAddress(clinicDetails.getAddress());
        });
        return clinicOpt;
    }


    public boolean delete(Long cnpj) {
        return clinics.removeIf(clinic -> clinic.getCnpj().equals(cnpj));
    }
}
