package com.valvarez.evaluation.payload.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class PhoneDto {
    private String number;
    private String citycode;
    private String contrycode;
}
