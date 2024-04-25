package com.example.myspringapp.student;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoSettings;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StudentServiceTest {

    @InjectMocks
    private StudentService studentService;

    //declare dependencies
    @Mock
    private StudentRepository repository;
    @Mock
    private StudentMapper mapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void should_successfully_save_a_student(){
        //Given
        StudentDTO studentDTO = new StudentDTO("John", "Doe","john@mail.com", 1);

        Student student = new Student("John", "Doe","john@mail.com", 24);

        Student savedStudent = new Student("John", "Doe","john@mail.com", 24);

        savedStudent.setID(1);

        //Mock the calls
        when(mapper.toStudent(studentDTO)).thenReturn(student);

        when(repository.save(student)).thenReturn(savedStudent);

        when(mapper.toStudentResponseDTO(savedStudent)).thenReturn(new StudentResponseDTO("John","Doe","john@mail.com"));

        //When
        StudentResponseDTO responseDTO = studentService.saveStudent(studentDTO);

        //Then
        assertEquals(studentDTO.firstname(), responseDTO.firstname());
        assertEquals(studentDTO.lastname(), responseDTO.lastname());
        assertEquals(studentDTO.email(), responseDTO.email());

        verify(mapper, times(1)).toStudent(studentDTO);
        verify(repository, times(1)).save(student);
        verify(mapper, times(1)).toStudentResponseDTO(savedStudent);
    }

    @Test
    public void should_return_all_students(){
        //Given
        List<Student> students = new ArrayList<>();
        students.add(new Student("John", "Doe","john@mail.com", 24));

        //Mock the calls
        when(repository.findAll()).thenReturn(students);
        when(mapper.toStudentResponseDTO(ArgumentMatchers.any(Student.class))).thenReturn(new StudentResponseDTO("John", "Doe","john@mail.com"));

        //When
        List<StudentResponseDTO> responseDTOs = studentService.findAllStudents();

        //Then
        assertEquals(students.size(), responseDTOs.size());
        verify(repository, times(1)).findAll();
    }

    @Test
    public void should_return_student_by_id(){
        //Given
        int StudentID = 1;

        Student student = new Student("John", "Doe","john@mail.com", 24);


        //Mock the calls
        when(repository.findById(StudentID)).thenReturn(Optional.of(student));

        when(mapper.toStudentResponseDTO(any(Student.class))).thenReturn(new StudentResponseDTO("John", "Doe","john@mail.com"));

        //When
        StudentResponseDTO dto = studentService.findStudentByID(StudentID);

        //Then
        assertEquals(dto.firstname(), student.getFirstname());
        assertEquals(dto.lastname(), student.getLastname());
        assertEquals(dto.email(), student.getEmail());

        verify(repository, times(1)).findById(StudentID);
    }

    @Test
    public void should_find_student_by_name(){
        //Given
        String studentName = "John";
        List<Student> students = new ArrayList<>();
        students.add(new Student("John", "Doe","john@mail.com", 24));

        //Mock the calls
        when(repository.findAllByFirstnameContainingIgnoreCase(studentName)).thenReturn(students);
        when(mapper.toStudentResponseDTO(any(Student.class))).thenReturn(new StudentResponseDTO("John", "Doe","john@mail.com"));

        //When
        var responseDTO = studentService.findStudentByName(studentName);

        //Then
        assertEquals(students.size(), responseDTO.size());
        verify(repository, times(1)).findAllByFirstnameContainingIgnoreCase(studentName);
    }
}