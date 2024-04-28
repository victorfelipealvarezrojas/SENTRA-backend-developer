package com.valvarez.evaluation.payload.dto.in;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PhoneDto {
    private String number;
    private String citycode;
    private String contrycode;
}
