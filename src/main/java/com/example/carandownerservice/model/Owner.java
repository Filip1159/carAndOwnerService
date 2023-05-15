package com.example.carandownerservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Owner {
    private int id;
    private String name;
    private String surname;
}
