package com.example.myspringapp.school;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SchoolRepository extends JpaRepository <School, Integer> {
}
