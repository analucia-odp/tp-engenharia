package com.ufmg.petclinic.model;

public class Animal {
    private String name;
    private String specie;
    private int age;
    private int weight;

    public Animal(String name, String specie, int age, int weight) {
        this.name = name;
        this.specie = specie;
        this.age = age;
        this.weight = weight;
    }
}
