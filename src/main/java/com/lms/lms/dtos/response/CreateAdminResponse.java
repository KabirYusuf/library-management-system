package com.lms.lms.dtos.response;

import lombok.Builder;
import lombok.Data;

@Data
public class CreateAdminResponse {
    private String email;
    private final String message = "Successful";
}
