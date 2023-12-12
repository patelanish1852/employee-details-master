package com.employeedetails.model;

import com.employeedetails.dto.DepartmentDTO;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;

@Where(clause = "status<>'DELETED'")
@Entity
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Enumerated(EnumType.STRING)
    private Status status;

    public Department(Long id, String name, Status status) {
        this.id = id;
        this.name = name;
        this.status = status;
    }

    public Department() {
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
