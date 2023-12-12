package com.employeedetails.repository;

import com.employeedetails.dto.DesignationDTO;
import com.employeedetails.model.Department;
import com.employeedetails.model.Designation;
import com.employeedetails.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DesignationRepository extends JpaRepository<Designation,Long> {
    @Query("select new com.employeedetails.dto.DesignationDTO(d) from Designation d")
    List<DesignationDTO> findAllByStatus(Status status);
}
