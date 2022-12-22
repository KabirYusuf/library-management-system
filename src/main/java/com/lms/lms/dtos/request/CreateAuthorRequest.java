package com.lms.lms.dtos.request;

import lombok.Data;

@Data
public class CreateAuthorRequest {
    private String firstName;
    private String lastName;
    private String email;
    private Long authorId;
}
