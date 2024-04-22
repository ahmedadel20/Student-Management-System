package com.example.myspringapp.student;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;

public record StudentDTO(
        @NotEmpty(message = "First Name should not be empty")
        String firstname,
        @NotEmpty(message = "Last Name should not be empty")
        String lastname,
        String email,
        Integer schoolId) {

}
