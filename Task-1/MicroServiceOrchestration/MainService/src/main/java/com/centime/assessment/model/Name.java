package com.centime.assessment.model;

import org.springframework.lang.NonNull;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class Name {

    @NotBlank(message = "name is mandatory")
    public String name;
    @NotBlank(message = "surname is Mandatory")
    public String surname;

    public Name(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public Name() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
