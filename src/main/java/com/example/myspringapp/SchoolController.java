package com.example.myspringapp;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class SchoolController {
    private final SchoolRepository schoolRepository;

    public SchoolController(SchoolRepository schoolRepository) {
        this.schoolRepository = schoolRepository;
    }

    @PostMapping("/schools")
    public SchoolDTO create(@RequestBody SchoolDTO schoolDTO){

        var school = toSchool(schoolDTO);
        var savedSchool = schoolRepository.save(school);
        return schoolDTO;
    }

    private School toSchool(SchoolDTO schoolDTO) {
        return new School(schoolDTO.name());
    }

    private SchoolDTO toSchoolDTO(School school){
        return new SchoolDTO(school.getName());
    }

    @GetMapping("/schools")
    public List<SchoolDTO> findAll(){
        return schoolRepository.findAll().stream().map(this::toSchoolDTO).collect(Collectors.toList());
    }
}
