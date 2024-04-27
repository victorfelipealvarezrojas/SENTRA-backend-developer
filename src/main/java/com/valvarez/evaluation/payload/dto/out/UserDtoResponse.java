package com.valvarez.evaluation.payload.dto.out;

import com.valvarez.evaluation.payload.dto.PhoneDto;
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
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime lastLogin;
    private List<PhoneDto> phones;
}
