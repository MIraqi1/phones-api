package com.tmo.phones;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "phones")
public class Phone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String make;
    private String model;
    private String os;
    private LocalDate releaseDate;

    public Phone() {
    }

    public Phone(String make, String model, String os) {
        this.make = make;
        this.model = model;
        this.os = os;
    }

    public Phone(String make, String model, String os, LocalDate releaseDate) {
        this.make = make;
        this.model = model;
        this.os = os;
        this.releaseDate = releaseDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }
}
