package com.poclab.coviddiary.model;

import lombok.Data;

import java.util.Date;

@Data
public class SignUpModel {

    private String patient_email;
    private String patient_phone;
    private String patient_first_name;
    private String patient_last_name;

    private Double weight;
    private Double height;
    private Date covid_test_date;
    private Date date_of_birth;

    private String doctor_email;
    private String doctor_phone;
}
