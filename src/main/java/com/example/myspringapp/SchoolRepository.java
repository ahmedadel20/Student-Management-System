package com.example.myspringapp;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SchoolRepository extends JpaRepository <School, Integer> {
}
