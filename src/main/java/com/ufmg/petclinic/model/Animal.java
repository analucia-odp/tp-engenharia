package com.ufmg.petclinic.model;

import com.fasterxml.jackson.annotation.JsonProperty;
public class Animal {
    private final String name;
    private final String specie;
    private final int age;
    private final int weight;

    public Animal(@JsonProperty("name") String name,
                  @JsonProperty("specie") String specie,
                  @JsonProperty("age") int age,
                  @JsonProperty("weight") int weight) {
        this.name = name;
        this.specie = specie;
        this.age = age;
        this.weight = weight;
    }
    public String getName() {
        return name;
    }

    public String getSpecie() {
        return specie;
    }

    public int getAge() {
        return age;
    }

    public int getWeight() {
        return weight;
    }
}
