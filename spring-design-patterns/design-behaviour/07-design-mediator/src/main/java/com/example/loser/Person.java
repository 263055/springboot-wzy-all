package com.example.loser;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Person {
    protected String name;
    protected MediatorCompany mediatorCompany;
}