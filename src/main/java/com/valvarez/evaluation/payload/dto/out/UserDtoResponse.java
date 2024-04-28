package com.valvarez.evaluation.payload.dto.out;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.valvarez.evaluation.payload.dto.in.PhoneDto;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Builder
public class UserDtoResponse {
    private UUID id;
    private String name;
    private String email;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime createdAt;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime updatedAt;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastLogin;
    private List<PhoneDto> phones;
}
