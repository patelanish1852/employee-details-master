package com.employeedetails.controller;

import com.employeedetails.dto.DepartmentDTO;
import com.employeedetails.exception.DepartmentCreationException;
import com.employeedetails.exception.DepartmentDeletionException;
import com.employeedetails.exception.DepartmentNotFoundException;
import com.employeedetails.exception.DepartmentUpdateException;
import com.employeedetails.model.Status;
import com.employeedetails.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/departments")
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    // Create a Department
    @PostMapping
    public ResponseEntity<DepartmentDTO> createDepartment(@RequestBody DepartmentDTO departmentDTO) {
        try {
            DepartmentDTO createdDepartment = departmentService.createDepartment(departmentDTO);
            return new ResponseEntity<>(createdDepartment, HttpStatus.CREATED);
        } catch (Exception e) {
            throw new DepartmentCreationException("Failed to create department: " + e.getMessage());
        }
    }

    // Get all Departments
    @GetMapping("/all")
    public ResponseEntity<List<DepartmentDTO>> getAllDepartments() {
        List<DepartmentDTO> departments = departmentService.getAllDepartments();
        return new ResponseEntity<>(departments, HttpStatus.OK);
    }

    // Get Department by ID
    @GetMapping("/{id}")
    public ResponseEntity<DepartmentDTO> getDepartmentById(@PathVariable Long id) {
        try {
            DepartmentDTO department = departmentService.getDepartmentById(id);
            if (department != null) {
                return new ResponseEntity<>(department, HttpStatus.OK);
            } else {
                throw new DepartmentNotFoundException("Department not found with ID: " + id);
            }
        } catch (Exception e) {
            throw new DepartmentNotFoundException("Error retrieving department: " + e.getMessage());
        }
    }

    // Update a Department
    @PutMapping("/{id}")
    public ResponseEntity<DepartmentDTO> updateDepartment(@PathVariable Long id, @RequestBody DepartmentDTO departmentDTO) {
        try {
            DepartmentDTO updatedDepartment = departmentService.updateDepartment(id, departmentDTO);
            if (updatedDepartment != null) {
                return new ResponseEntity<>(updatedDepartment, HttpStatus.OK);
            } else {
                throw new DepartmentUpdateException("Department not found with ID: " + id);
            }
        } catch (Exception e) {
            throw new DepartmentUpdateException("Error updating department: " + e.getMessage());
        }
    }

    // Delete a Department
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDepartment(@PathVariable Long id) {
        try {
            departmentService.softDeleteDepartment(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            throw new DepartmentDeletionException("Error deleting department: " + e.getMessage());
        }
    }


    @GetMapping("/status/{status}")
    public ResponseEntity<List<DepartmentDTO>> getDepartmentsByStatus(@PathVariable Status status) {
        List<DepartmentDTO> departments = departmentService.getAllDepartmentsByStatus(status);
        if (departments.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(departments, HttpStatus.OK);
    }
}