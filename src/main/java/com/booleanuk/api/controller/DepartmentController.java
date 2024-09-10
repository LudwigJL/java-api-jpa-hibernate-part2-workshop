package com.booleanuk.api.controller;

import com.booleanuk.api.model.Department;
import com.booleanuk.api.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("departments")
public class DepartmentController {
    @Autowired
    private DepartmentRepository departmentRepository;

    @GetMapping
    public ResponseEntity<List<Department>> findAllByLocation(@RequestParam(required = false) String location) {
        if (location == null) {
            return ResponseEntity.ok(this.departmentRepository.findAll());
        }
        List<Department> departments = this.departmentRepository.findAllByLocation(location);

        return ResponseEntity.ok(departments);
    }

    @PostMapping
    public ResponseEntity<Department> createDepartment(@RequestBody Department department) {
        return new ResponseEntity<Department>(this.departmentRepository.save(department), HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<Department> updateDepartment(@PathVariable int id, @RequestBody Department department) {
        Department departmentToUpdate = this.departmentRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Not Found"));
        departmentToUpdate.setName(department.getName());
        departmentToUpdate.setLocation(department.getLocation());

        return new ResponseEntity<Department>(this.departmentRepository.save(departmentToUpdate), HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Department> deleteDepartment(@PathVariable int id) {
        Department departmentToDelete = this.departmentRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        this.departmentRepository.delete(departmentToDelete);
        return new ResponseEntity<>(departmentToDelete, HttpStatus.OK);
    }
}