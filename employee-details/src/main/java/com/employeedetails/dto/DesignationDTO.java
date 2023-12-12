package com.employeedetails.dto;


import com.employeedetails.model.Designation;
import com.employeedetails.model.Status;

public class DesignationDTO {

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


    public Designation toEntity(DesignationDTO designationDTO) {
        Designation designation = new Designation();
        designation.setName(designationDTO.getName());
        designation.setStatus(designation.getStatus(Status.CREATED));
        return designation;
    }

    public DesignationDTO(){}
    public DesignationDTO(Designation designation) {
        this.id = designation.getId();
        this.name = designation.getName();
        this.status = designation.getStatus();
    }
}