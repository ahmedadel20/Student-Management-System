package com.example.myspringapp;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {
    private final StudentRepository studentRepository;


    public StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @PostMapping("/students")
    public StudentResponseDTO post(@RequestBody StudentDTO studentDTO)
    {
        var student = toStudent(studentDTO);
        var savedStudent = studentRepository.save(student);
        return toStudentResponseDTO(savedStudent);
    }

    private Student toStudent(StudentDTO studentDTO){
        var student = new Student();
        student.setFirstname(studentDTO.firstname());
        student.setLastname(studentDTO.lastname());
        student.setEmail(studentDTO.email());

        var school = new School();
        school.setId(studentDTO.schoolId());

        student.setSchool(school);

        return student;
    }

    private StudentResponseDTO toStudentResponseDTO(Student student){
        return new StudentResponseDTO(student.getFirstname(),
                student.getLastname(), student.getEmail());
    }

    @GetMapping("/students")
    public List<Student> findAllStudents()
    {
        return studentRepository.findAll();
    }

    @GetMapping("/students/{student-id}")
    public Student findStudentByID(@PathVariable("student-id") Integer id)
    {
        return studentRepository.findById(id).orElse(new Student());
    }

    @GetMapping("/students/search/{student-name}")
    public List<Student> findStudentsByName(@PathVariable("student-name") String firstname)
    {
        return studentRepository.findAllByFirstnameContainingIgnoreCase(firstname);
    }

    @DeleteMapping("/students/{student-id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteStudent(@PathVariable("student-id") Integer id)
    {
        studentRepository.deleteById(id);
    }
}
