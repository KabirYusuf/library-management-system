package com.lms.lms.data.models;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.Instant;
@Data
@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
//@Table(name = "baseUser")
public class User {
    @Column(nullable = false, length = 50)
    private String firstName;
    @Column(nullable = false, length = 50)
    private String lastName;
    @Column(nullable = false, length = 50, unique = true)
    private String email;
    @Column(nullable = false, length = 50)
    private String password;
    @Column(nullable = false, length = 50)
    private String phoneNumber;
    private Instant createdDate = Instant.now();
}
