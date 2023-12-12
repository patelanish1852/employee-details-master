package com.employeedetails.serviceimpl;

import com.employeedetails.dto.EmployeeDTO;
import com.employeedetails.model.Employee;
import com.employeedetails.model.Status;
import com.employeedetails.repository.EmployeeRepository;
import com.employeedetails.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public EmployeeDTO createEmployee(EmployeeDTO employeeDTO) {
        Employee savedEmployee = employeeRepository.save(employeeDTO.toEntity(employeeDTO));
            return employeeDTO;
        }

    @Override
    public List<EmployeeDTO> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        List<EmployeeDTO> employeeDTOs = new ArrayList<>();

        employees.forEach(employee->{
            EmployeeDTO employeeDTO = new EmployeeDTO();
            employeeDTO.setId(employee.getId());
            employeeDTO.setName(employee.getName());
            employeeDTO.setDepartmentId(employee.getDepartmentId());
            employeeDTO.setDesignationId(employee.getDesignationId());
            employeeDTOs.add(employeeDTO);
        });
        return employeeDTOs;
    }

    @Override
    public EmployeeDTO getEmployeeById(Long id) {
        Optional<Employee> employeeOptional = employeeRepository.findById(id);

        if (employeeOptional.isPresent()) {
            Employee employee = employeeOptional.get();
            EmployeeDTO employeeDTO = new EmployeeDTO();
            employeeDTO.setId(employee.getId());
            employeeDTO.setName(employee.getName());
            employeeDTO.setDepartmentId(employee.getDepartmentId());
            employeeDTO.setDesignationId(employee.getDesignationId());

            return employeeDTO;
        } else {
            return null; // Employee with the given ID doesn't exist
        }
    }

    @Override
    public EmployeeDTO updateEmployee(Long id, EmployeeDTO employeeDTO) {
        Optional<Employee> employeeOptional = employeeRepository.findById(id);
            if (employeeOptional.isPresent()) {
                Employee employee = employeeOptional.get();
                employee.setId(id);
                employee.setName(employeeDTO.getName());
                employee.setDepartmentId(employeeDTO.getDepartmentId());
                employee.setDesignationId(employeeDTO.getDesignationId());
                employeeRepository.save(employee);
                return employeeDTO;
            }
        return null; // Employee with the given ID doesn't exist
    }

    @Override
    public void deleteEmployee(Long id) {
        Optional<Employee> employeeOptional = employeeRepository.findById(id);

        if (employeeOptional.isPresent()) {
            employeeRepository.delete(employeeOptional.get());
        }
    }

    public void softDeleteEmployee(Long id) {
        Optional<Employee> employeeOptional = employeeRepository.findById(id);

        employeeOptional.ifPresent(employee -> {
            employee.setStatus(Status.DELETED); // Set the status to DELETED
            employeeRepository.save(employee); // Save the updated employee with DELETED status
        });
    }


    @Override
    public List<EmployeeDTO> getAllEmployeesByStatus(Status status) {
        List<EmployeeDTO> employeeOptional = employeeRepository.findAllByStatus(status);
        return employeeOptional;
    }
}