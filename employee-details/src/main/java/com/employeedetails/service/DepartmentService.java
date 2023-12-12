package com.employeedetails.service;

import com.employeedetails.dto.DepartmentDTO;
import com.employeedetails.model.Status;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentService {
    DepartmentDTO createDepartment(DepartmentDTO departmentDTO);

    List<DepartmentDTO> getAllDepartments();

    DepartmentDTO getDepartmentById(Long id);

    DepartmentDTO updateDepartment(Long id, DepartmentDTO departmentDTO);

    void softDeleteDepartment(Long id);

    void deleteDepartment(Long id);

    List<DepartmentDTO> getAllDepartmentsByStatus(Status status);

    /*List<DepartmentDTO> getCreatedDepartments();*/
}