package com.example.carandownerservice.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "owner")
public class Owner {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private int id;
    private String name;
    private String surname;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Date dateOfBirth;
    private boolean isPremiumCustomer;

    @ManyToMany
    @JoinTable(name = "ownership",
            joinColumns = { @JoinColumn(name = "owner_id")},
            inverseJoinColumns = {@JoinColumn(name = "car_id")})
    private List<Car> cars;
}
