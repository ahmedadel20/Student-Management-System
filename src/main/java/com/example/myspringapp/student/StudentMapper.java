package com.example.myspringapp.student;

import com.example.myspringapp.school.School;
import org.springframework.stereotype.Service;

@Service
public class StudentMapper {

    public Student toStudent(StudentDTO studentDTO){

        if(studentDTO == null){
            throw new NullPointerException("The student DTO should not be null");
        }

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
