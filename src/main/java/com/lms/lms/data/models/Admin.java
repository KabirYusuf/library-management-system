package com.lms.lms.data.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@SuperBuilder
public class Admin extends User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

}
