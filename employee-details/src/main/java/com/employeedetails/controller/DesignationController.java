package com.employeedetails.controller;

import com.employeedetails.dto.DesignationDTO;
import com.employeedetails.exception.DesignationCreationException;
import com.employeedetails.exception.DesignationDeletionException;
import com.employeedetails.exception.DesignationNotFoundException;
import com.employeedetails.exception.DesignationUpdateException;
import com.employeedetails.model.Status;
import com.employeedetails.service.DesignationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/designations")
public class DesignationController {
    @Autowired
    private DesignationService designationService;

    // Create a Designation
    @PostMapping
    public ResponseEntity<DesignationDTO> createDesignation(@RequestBody DesignationDTO designationDTO) {
        try {
            DesignationDTO createdDesignation = designationService.createDesignation(designationDTO);
            return new ResponseEntity<>(createdDesignation, HttpStatus.CREATED);
        } catch (Exception e) {
            throw new DesignationCreationException("Failed to create designation: " + e.getMessage());
        }
    }

    // Get all Designations
    @GetMapping("/all")
    public ResponseEntity<List<DesignationDTO>> getAllDesignations() {
        List<DesignationDTO> designations = designationService.getAllDesignations();
        return new ResponseEntity<>(designations, HttpStatus.OK);
    }

    // Get Designation by ID
    @GetMapping("/{id}")
    public ResponseEntity<DesignationDTO> getDesignationById(@PathVariable Long id) {
        try {
            DesignationDTO designation = designationService.getDesignationById(id);
            if (designation != null) {
                return new ResponseEntity<>(designation, HttpStatus.OK);
            } else {
                throw new DesignationNotFoundException("Designation not found with ID: " + id);
            }
        } catch (Exception e) {
            throw new DesignationNotFoundException("Error retrieving designation: " + e.getMessage());
        }
    }

    // Update a Designation
    @PutMapping("/{id}")
    public ResponseEntity<DesignationDTO> updateDesignation(@PathVariable Long id, @RequestBody DesignationDTO designationDTO) {
        try {
            DesignationDTO updatedDesignation = designationService.updateDesignation(id, designationDTO);
            if (updatedDesignation != null) {
                return new ResponseEntity<>(updatedDesignation, HttpStatus.OK);
            } else {
                throw new DesignationUpdateException("Designation not found with ID: " + id);
            }
        } catch (Exception e) {
            throw new DesignationUpdateException("Error updating designation: " + e.getMessage());
        }
    }

    // Delete a Designation
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDesignation(@PathVariable Long id) {
        try {
            designationService.softDeleteDesignation(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            throw new DesignationDeletionException("Error deleting designation: " + e.getMessage());
        }
    }


    @GetMapping("/status/{status}")
    public ResponseEntity<List<DesignationDTO>> findAllByStatus(@PathVariable Status status) {
        List<DesignationDTO> designations = designationService.getAllDesignationsByStatus(status);
        return new ResponseEntity<>(designations, HttpStatus.OK);
    }
}