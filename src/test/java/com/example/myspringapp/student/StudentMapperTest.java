package com.example.myspringapp.student;

import jakarta.validation.constraints.Null;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.xml.stream.events.DTD;

import static org.junit.jupiter.api.Assertions.*;

class StudentMapperTest {

    private StudentMapper mapper;

    @BeforeEach
    void setUp(){
        mapper = new StudentMapper();
    }

    @Test
    public void shouldMapStudentDtoToStudent(){
        StudentDTO dto = new StudentDTO("John", "Doe", "john@email.com", 1);

        Student student = mapper.toStudent(dto);

        assertEquals(dto.firstname(), student.getFirstname());
        assertEquals(dto.lastname(), student.getLastname());
        assertEquals(dto.email(), student.getEmail());
        assertNotNull(student.getSchool());
        assertEquals(dto.schoolId(), student.getSchool().getId());
    }

    @Test
    public void should_throw_a_null_pointer_exception_when_studentDTO_is_null(){
        var exp = assertThrows(NullPointerException.class, () -> mapper.toStudent(null));
        assertEquals("The student DTO should not be null", exp.getMessage());
    }


    @Test
    public void shouldMapStudentToStudentResponseDto(){
        //Given
        Student student = new Student("Ahmed", "kawy", "ahmed@gmail.com", 24);

        //When
        StudentResponseDTO dto = mapper.toStudentResponseDTO(student);

        //Then
        assertEquals(student.getFirstname(), dto.firstname());
        assertEquals(student.getLastname(), dto.lastname());
        assertEquals(student.getEmail(), dto.email());
    }

}