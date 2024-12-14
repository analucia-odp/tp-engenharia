package com.ufmg.petclinic.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.ufmg.petclinic.model.Clinic;


@Repository
public class ClinicRepository {
    private List<Clinic> clinics = new ArrayList<>();

    public Clinic save(Clinic clinic) {
        clinics.add(clinic);
        return clinic;
    }

    public Optional<Clinic> findById(UUID id) {
        return clinics.stream()
                .filter(clinic -> clinic.getId().equals(id))
                .findFirst();
    }

    public List<Clinic> findAll() {
        return clinics;
    }

    public boolean deleteById(UUID id) {
        return clinics.removeIf(clinic -> clinic.getId().equals(id));
    }

    public Optional<Clinic> update(UUID id, Clinic updatedClinic) {
        for (int i = 0; i < clinics.size(); i++) {
            if (clinics.get(i).getId().equals(id)) {
                Clinic existingClinic = clinics.get(i);
                existingClinic.setName(updatedClinic.getName());
                existingClinic.setAddress(updatedClinic.getAddress());
                existingClinic.setCnpj(updatedClinic.getCnpj());
                return Optional.of(existingClinic);
            }
        }
        return Optional.empty();
    }
}