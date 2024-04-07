package com.example.myspringapp;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {
    private final StudentRepository repository;
    private final StudentMapper studentMapper;

    public StudentService(StudentRepository studentRepository, StudentMapper studentMapper) {
        this.repository = studentRepository;
        this.studentMapper = studentMapper;
    }

    public StudentResponseDTO saveStudent(StudentDTO studentDTO){
        var student = studentMapper.toStudent(studentDTO);
        var savedStudent = repository.save(student);
        return studentMapper.toStudentResponseDTO(savedStudent);
    }

    public List<StudentResponseDTO> findAllStudents(){
        return repository.findAll().stream().map(studentMapper::toStudentResponseDTO).collect(Collectors.toList());
    }

    public StudentResponseDTO findStudentByID(Integer id){
        return repository.findById(id).map(studentMapper::toStudentResponseDTO).orElse(null);
    }

    public List<StudentResponseDTO> findStudentByName(String firstname){
        return repository.findAllByFirstnameContainingIgnoreCase(firstname).stream().map(studentMapper::toStudentResponseDTO).collect(Collectors.toList());
    }

    public void deleteStudent(Integer id) {
        repository.deleteById(id);
    }
}
