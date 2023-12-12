package com.employeedetails.serviceimpl;

import com.employeedetails.dto.DesignationDTO;
import com.employeedetails.model.Designation;
import com.employeedetails.model.Status;
import com.employeedetails.repository.DesignationRepository;
import com.employeedetails.service.DesignationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DesignationServiceImpl implements DesignationService {
    @Autowired
    private DesignationRepository designationRepository;

    @Override
    public DesignationDTO createDesignation(DesignationDTO designationDTO) {
        designationRepository.save(designationDTO.toEntity(designationDTO));
        return designationDTO;
    }

    @Override
    public List<DesignationDTO> getAllDesignations() {
        List<Designation> designations = designationRepository.findAll();
        List<DesignationDTO> designationDTOs = new ArrayList<>();

        designations.forEach(designation->{
            DesignationDTO designationDTO = new DesignationDTO();
            designationDTO.setId(designation.getId());
            designationDTO.setName(designation.getName());
            designationDTOs.add(designationDTO);
        });
        return designationDTOs;
    }

    @Override
    public DesignationDTO getDesignationById(Long id) {
        Optional<Designation> designationOptional = designationRepository.findById(id);

        if (designationOptional.isPresent()) {
            Designation designation = designationOptional.get();
            DesignationDTO designationDTO = new DesignationDTO();
            designationDTO.setId(designation.getId());
            designationDTO.setName(designation.getName());
            return designationDTO;
        }
        else {
            return null; // Designation with the given ID doesn't exist
        }
    }

    @Override
    public DesignationDTO updateDesignation(Long id, DesignationDTO designationDTO) {
        Optional<Designation> designationOptional = designationRepository.findById(id);

        if (designationRepository.existsById(id)) {
            Designation updatedDesignation = designationOptional.get();
            updatedDesignation.setName(designationDTO.getName());
            designationRepository.save(updatedDesignation);
            return designationDTO;
        }
        return null; // Designation with the given ID doesn't exist
    }

    @Override
    public void deleteDesignation(Long id) {
        Optional<Designation> designationOptional = designationRepository.findById(id);

        if (designationOptional.isPresent()) {
            designationRepository.delete(designationOptional.get());
        }
    }

    public void softDeleteDesignation(Long id) {
        Optional<Designation> designationOptional = designationRepository.findById(id);

        designationOptional.ifPresent(designation -> {
            designation.setStatus(Status.DELETED); // Set the status to DELETED
            designationRepository.save(designation); // Save the updated designation with DELETED status
        });
    }


    @Override
    public List<DesignationDTO> getAllDesignationsByStatus(Status status) {
        List<DesignationDTO> designationOptional = designationRepository.findAllByStatus(status);
        return designationOptional;
    }
}