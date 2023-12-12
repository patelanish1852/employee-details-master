package com.employeedetails.model;

import com.employeedetails.dto.EmployeeDTO;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Where(clause = "status<>'DELETED'")
@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Enumerated(EnumType.STRING)
    private Status status;


    @ManyToOne(cascade = CascadeType.ALL,targetEntity = Department.class,fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id",insertable = false,updatable = false)
    private Department department;
    @Column(name = "department_id")
    private Long departmentId;


    @ManyToOne(cascade = CascadeType.ALL,targetEntity = Designation.class,fetch = FetchType.LAZY)
    @JoinColumn(name = "designation_id",insertable = false,updatable = false)
    private Designation designation;
    @Column(name = "designation_id")
    private Long designationId;

    public Employee(Long id, String name, Status status) {
        this.id = id;
        this.name = name;
        this.status = status;
    }

    public Employee() {
        this.status = Status.CREATED;
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

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Designation getDesignation() {
        return designation;
    }

    public void setDesignation(Designation designation) {
        this.designation = designation;
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Status getStatus(Status status) {
        return status;
    }
}