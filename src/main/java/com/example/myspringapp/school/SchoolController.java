package com.example.myspringapp.school;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class SchoolController {

    private final SchoolService schoolService;

    public SchoolController(SchoolService schoolService) {
        this.schoolService = schoolService;
    }

    @PostMapping("/schools")
    public SchoolDTO create(
            @Valid @RequestBody SchoolDTO schoolDTO){

        return schoolService.create(schoolDTO);
    }

    @GetMapping("/schools")
    public List<SchoolDTO> findAll(){
        return schoolService.findAll();
    }
}
