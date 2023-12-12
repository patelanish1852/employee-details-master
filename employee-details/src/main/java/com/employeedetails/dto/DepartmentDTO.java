package com.employeedetails.dto;

import com.employeedetails.model.Department;
import com.employeedetails.model.Status;

public class DepartmentDTO {
    private Long id;
    private String name;

    private Status status;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Long getId() {

        return id;
    }

    public void setId(Long id) {

        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public Department toEntity(DepartmentDTO departmentDTO) {
        Department department = new Department();
        department.setName(departmentDTO.getName());
        department.setStatus(department.getStatus(Status.CREATED));
        return department;
    }

    public DepartmentDTO(){}
    public DepartmentDTO(Department department) {
        this.id = department.getId();
        this.name = department.getName();
        this.status = department.getStatus();
    }
}