package com.ufmg.petclinic.model;

import java.util.UUID;

public class Clinic {
 
    private UUID id;
    private Long cnpj;
    private String name;
    private String address;

    public Clinic(long cnpj, String name, String address) {
        this.id = UUID.randomUUID();
        this.cnpj = cnpj;
        this.name = name;
        this.address = address;
    }

    public UUID getId() {
        return id;
    }
    
    public void setId(UUID id) {
        this.id = id;
    }

    public Long getCnpj() {
        return cnpj;
    }

    public void setCnpj(Long cnpj) {
        this.cnpj = cnpj;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}

