package com.ufmg.petclinic.model;

//import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.lang.NonNull;

import java.util.UUID;

//@NoArgsConstructor
public class User {
    private final UUID id;
    @NonNull
    private final String name;
    @NonNull
    private final String cpf;
    @NonNull
    private final Animal animal;
    @NonNull
    private final String email;
    private final String phone;
    private final String address;

    public User(@JsonProperty("id") UUID id,
                @JsonProperty("name") String name,
                @JsonProperty("email") String email,
                @JsonProperty("cpf") String cpf,
                @JsonProperty("phone") String phone,
                @JsonProperty("address") String address,
                @JsonProperty("animal") Animal animal) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.cpf = cpf;
        this.phone = phone;
        this.address = address;
        this.animal = animal;
    }

    public UUID getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getEmail() {
        return email;
    }
    public String getCpf() {
        return cpf;
    }
    public String getPhone() {
        return phone;
    }
    public String getAddress() {
        return address;
    }
    public Animal getAnimal() {
        return animal;
    }
}
