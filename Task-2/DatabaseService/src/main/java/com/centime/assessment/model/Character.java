package com.centime.assessment.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.lang.NonNullFields;

import javax.persistence.*;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@Table(name = "Character")
public class Character {
    @Id
    @JsonIgnore
    public Long id;
    @JsonIgnore
    @Column(name = "parentid")
    public Long parentId;

    @JsonProperty("Name")
    @Column(name = "name")
    public String name;

    public List<Character> getSubclasses() {
        return subclasses;
    }

    public void setSubclasses(List<Character> subclasses) {
        this.subclasses = subclasses;
    }

    @JsonProperty("Color")
    @Column(name = "color")
    public String color;

    @JsonProperty("Sub Classes")
    @Transient
    public List<Character> subclasses;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Character{" +
                "id=" + id +
                ", parentId=" + parentId +
                ", name='" + name + '\'' +
                ", color='" + color + '\'' +
                ", subclasses=" + subclasses +
                '}';
    }
}
