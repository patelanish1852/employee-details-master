package com.employeedetails.repository;

import com.employeedetails.dto.DepartmentDTO;
import com.employeedetails.model.Department;
import com.employeedetails.model.Employee;
import com.employeedetails.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<Department,Long> {
    @Query("select new com.employeedetails.dto.DepartmentDTO(d) from Department d")
    List<DepartmentDTO> findAllByStatus(Status status);
}