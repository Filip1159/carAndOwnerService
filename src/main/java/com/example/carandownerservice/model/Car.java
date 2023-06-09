package com.example.carandownerservice.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "car")
public class Car {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private int id;

    private String brand;
    private String model;
    private int year;
    private Date firstRegistration;

    @ManyToMany
    @JoinTable(name = "ownership",
            joinColumns = { @JoinColumn(name = "car_id")},
            inverseJoinColumns = { @JoinColumn(name = "owner_id")})
    private List<Owner> owners;

    @OneToMany
    @JoinColumn(name = "car_id")
    private List<Inspection> inspections;
}
