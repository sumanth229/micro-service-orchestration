package com.centime.assessment.model;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

public class Name implements Serializable {

    @NotBlank(message = "name is mandatory")
    public String name;
    @NotBlank(message = "surname is Mandatory")
    public String surname;

    public Name(String name, String surname) {
        this.name = name;
        this.surname = surname;
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

    @Override
    public String toString() {
        return "Name{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }
}
