package com.example.myspringapp;

import org.springframework.stereotype.Service;

@Service
public class StudentMapper {

    public Student toStudent(StudentDTO studentDTO){
        var student = new Student();
        student.setFirstname(studentDTO.firstname());
        student.setLastname(studentDTO.lastname());
        student.setEmail(studentDTO.email());

        var school = new School();
        school.setId(studentDTO.schoolId());

        student.setSchool(school);

        return student;
    }

    public StudentResponseDTO toStudentResponseDTO(Student student){
        return new StudentResponseDTO(student.getFirstname(),
                student.getLastname(), student.getEmail());
    }
}
