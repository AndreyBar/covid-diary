package com.poclab.coviddiary.model;

import lombok.Data;

@Data
public class QuestionnaireModel {
    private Boolean highTemperature;
    private Boolean breathShortness;
    private Boolean dryCough;
    private String properties;
    private String patientName;
    private String token;
    private Integer age;
    private Boolean highFeverPrevious;

}
