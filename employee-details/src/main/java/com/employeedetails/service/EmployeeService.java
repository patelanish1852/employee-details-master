package com.employeedetails.service;

import com.employeedetails.dto.EmployeeDTO;
import com.employeedetails.model.Employee;
import com.employeedetails.model.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public interface EmployeeService {

    EmployeeDTO createEmployee(EmployeeDTO employeeDTO);

    List<EmployeeDTO> getAllEmployees();

    EmployeeDTO getEmployeeById(Long id);

    EmployeeDTO updateEmployee(Long id, EmployeeDTO employeeDTO);

    void softDeleteEmployee(Long id);

    void deleteEmployee(Long id);

    List<EmployeeDTO> getAllEmployeesByStatus(Status status);
}
