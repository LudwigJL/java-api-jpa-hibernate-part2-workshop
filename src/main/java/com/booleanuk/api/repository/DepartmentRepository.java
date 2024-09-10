package com.booleanuk.api.repository;

import com.booleanuk.api.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {
    List<Department> findAllByLocation(String location);
}