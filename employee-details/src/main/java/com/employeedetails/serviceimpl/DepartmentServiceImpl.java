package com.employeedetails.serviceimpl;

import com.employeedetails.dto.DepartmentDTO;
import com.employeedetails.model.Department;
import com.employeedetails.model.Status;
import com.employeedetails.repository.DepartmentRepository;
import com.employeedetails.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public DepartmentDTO createDepartment(DepartmentDTO departmentDTO) {
        departmentRepository.save(departmentDTO.toEntity(departmentDTO));
        return departmentDTO;
    }

    @Override
    public List<DepartmentDTO> getAllDepartments() {
        List<Department> departments = departmentRepository.findAll();
        List<DepartmentDTO> departmentDTOs = new ArrayList<>();

        departments.forEach(dept -> {
            DepartmentDTO departmentDTO = new DepartmentDTO();
            departmentDTO.setId(dept.getId());
            departmentDTO.setName(dept.getName());
            departmentDTOs.add(departmentDTO);
        });
        return departmentDTOs;
    }

    @Override
    public DepartmentDTO getDepartmentById(Long id) {
        Optional<Department> departmentOptional = departmentRepository.findById(id);

        if (departmentOptional.isPresent()) {
            Department department = departmentOptional.get();
            DepartmentDTO departmentDTO = new DepartmentDTO();
            departmentDTO.setId(department.getId());
            departmentDTO.setName(department.getName());
            return departmentDTO;
        } else {
            return null; // Department with the given ID doesn't exist
        }
    }

    @Override
    public DepartmentDTO updateDepartment(Long id, DepartmentDTO departmentDTO) {
        Optional<Department> departmentOptional = departmentRepository.findById(id);

        if (departmentOptional.isPresent()) {
            Department updatedDepartment = departmentOptional.get();
            updatedDepartment.setName(departmentDTO.getName());
            departmentRepository.save(updatedDepartment);
            return departmentDTO;
        }
        return null; // Department with the given ID doesn't exist
    }

    @Override
    public void deleteDepartment(Long id) {
        Optional<Department> departmentOptional = departmentRepository.findById(id);

        if (departmentOptional.isPresent()) {
            departmentRepository.delete(departmentOptional.get());
        }
    }

    public void softDeleteDepartment(Long id) {
        Optional<Department> departmentOptional = departmentRepository.findById(id);

        departmentOptional.ifPresent(department -> {
            department.setStatus(Status.DELETED); // Set the status to DELETED
            departmentRepository.save(department); // Save the updated department with DELETED status
        });
    }


    @Override
    public List<DepartmentDTO> getAllDepartmentsByStatus(Status status) {
        List<DepartmentDTO> departmentOptional = departmentRepository.findAllByStatus(status);
        return departmentOptional;
    }
}