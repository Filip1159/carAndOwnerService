package com.example.carandownerservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "inspection")
public class Inspection {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private int id;

    private Date date;

    private int mileage;

    private String comments;

    private boolean isPositive;

    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;
}
