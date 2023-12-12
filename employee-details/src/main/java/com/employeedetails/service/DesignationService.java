package com.employeedetails.service;

import com.employeedetails.dto.DesignationDTO;
import com.employeedetails.model.Status;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DesignationService {
    DesignationDTO createDesignation(DesignationDTO designationDTO);

    List<DesignationDTO> getAllDesignations();

    DesignationDTO getDesignationById(Long id);

    DesignationDTO updateDesignation(Long id, DesignationDTO designationDTO);

    void softDeleteDesignation(Long id);

    void deleteDesignation(Long id);

    List<DesignationDTO> getAllDesignationsByStatus(Status status);

    /*List<DesignationDTO> getCreatedDesignations();*/
}
