package com.employeedetails.controller;

import com.employeedetails.dto.EmployeeDTO;
import com.employeedetails.exception.EmployeeCreationException;
import com.employeedetails.exception.EmployeeDeletionException;
import com.employeedetails.exception.EmployeeNotFoundException;
import com.employeedetails.exception.EmployeeUpdateException;
import com.employeedetails.model.Status;
import com.employeedetails.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    // Create an Employee
    @PostMapping
    public ResponseEntity<EmployeeDTO> createEmployee(@RequestBody EmployeeDTO employeeDTO) {
        try {
            EmployeeDTO createdEmployee = employeeService.createEmployee(employeeDTO);
            return new ResponseEntity<>(createdEmployee, HttpStatus.CREATED);
        } catch (Exception e) {
            throw new EmployeeCreationException("Failed to create employee: " + e.getMessage());
        }
    }

    // Get all Employees
    @GetMapping("/all")
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees() {
        List<EmployeeDTO> employees = employeeService.getAllEmployees();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    // Get Employee by ID
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable Long id) {
        try {
            EmployeeDTO employee = employeeService.getEmployeeById(id);
            if (employee != null) {
                return new ResponseEntity<>(employee, HttpStatus.OK);
            } else {
                throw new EmployeeNotFoundException("Employee not found with ID: " + id);
            }
        } catch (Exception e) {
            throw new EmployeeNotFoundException("Error retrieving employee: " + e.getMessage());
        }
    }

    // Update an Employee
    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDTO> updateEmployee(@PathVariable Long id, @RequestBody EmployeeDTO employeeDTO) {
        try {
            EmployeeDTO updatedEmployee = employeeService.updateEmployee(id, employeeDTO);
            if (updatedEmployee != null) {
                return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);
            } else {
                throw new EmployeeUpdateException("Employee not found with ID: " + id);
            }
        } catch (Exception e) {
            throw new EmployeeUpdateException("Error updating employee: " + e.getMessage());
        }
    }

    // Delete an Employee
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        try {
            employeeService.softDeleteEmployee(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            throw new EmployeeDeletionException("Error deleting employee: " + e.getMessage());
        }
    }


    @GetMapping("/status/{status}")
    public ResponseEntity<List<EmployeeDTO>> getEmployeesByStatus(@PathVariable Status status) {
        List<EmployeeDTO> employees = employeeService.getAllEmployeesByStatus(status);
        if (employees.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }
}