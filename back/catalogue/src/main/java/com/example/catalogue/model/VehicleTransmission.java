package com.example.catalogue.model;

import javax.persistence.*;

@Entity
public class VehicleTransmission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="transmission", nullable = false, unique = true)
    private String value;

    public VehicleTransmission() {
    }

    public VehicleTransmission(String value) {
        this.value = value;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "VehicleTransmission{" +
                "id=" + id +
                ", value='" + value + '\'' +
                '}';
    }
}
