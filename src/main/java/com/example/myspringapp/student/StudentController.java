package com.example.myspringapp.student;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/students")
    public StudentResponseDTO saveStudent(
            @Valid @RequestBody StudentDTO studentDTO)
    {
        return this.studentService.saveStudent(studentDTO);
    }

    @GetMapping("/students")
    public List<StudentResponseDTO> findAllStudents()
    {
        return studentService.findAllStudents();
    }

    @GetMapping("/students/{student-id}")
    public StudentResponseDTO findStudentByID(@PathVariable("student-id") Integer id)
    {
        return studentService.findStudentByID(id);
    }

    @GetMapping("/students/search/{student-name}")
    public List<StudentResponseDTO> findStudentsByName(@PathVariable("student-name") String firstname)
    {
        return studentService.findStudentByName(firstname);
    }

    @DeleteMapping("/students/{student-id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteStudent(@PathVariable("student-id") Integer id)
    {
        studentService.deleteStudent(id);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException exp){
        var errors = new HashMap<String, String>();
        exp.getBindingResult().getAllErrors().forEach(error-> {var fieldName = ((FieldError) error).getField();
        var errorMessage = error.getDefaultMessage();
        errors.put(fieldName, errorMessage);
        });

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}
