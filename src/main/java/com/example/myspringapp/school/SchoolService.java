package com.example.myspringapp.school;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SchoolService {

    private final SchoolRepository schoolRepository;

    private final SchoolMapper schoolMapper;

    public SchoolService(SchoolRepository schoolRepository, SchoolMapper schoolMapper) {
        this.schoolRepository = schoolRepository;
        this.schoolMapper = schoolMapper;
    }

    public List<SchoolDTO> findAll(){
        return schoolRepository.findAll().stream().map(schoolMapper::toSchoolDTO).collect(Collectors.toList());
    }

    public SchoolDTO create(SchoolDTO schoolDTO){
        var school = schoolMapper.toSchool(schoolDTO);
        var savedSchool = schoolRepository.save(school);
        return schoolDTO;
    }
}
