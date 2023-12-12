package com.employeedetails.dto;

import com.employeedetails.model.Employee;
import com.employeedetails.model.Status;

public class EmployeeDTO {
    private Long id;
    private String name;
    private Long departmentId;
    private Long designationId;
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

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public Long getDesignationId() {
        return designationId;
    }

    public void setDesignationId(Long designationId) {
        this.designationId = designationId;
    }

    public Employee toEntity(EmployeeDTO employeeDTO){
        Employee employee = new Employee();
        employee.setId(employeeDTO.getId());
        employee.setName(employeeDTO.getName());
        employee.setDepartmentId(employeeDTO.getDepartmentId());
        employee.setDesignationId(employeeDTO.getDesignationId());
        employee.setStatus(employee.getStatus(Status.CREATED));
        return employee;
    }

    public EmployeeDTO(){}
    public EmployeeDTO(Employee employee) {
        this.id = employee.getId();
        this.name = employee.getName();
        this.departmentId = employee.getDepartmentId();
        this.designationId = employee.getDesignationId();
        this.status = employee.getStatus();
    }
}