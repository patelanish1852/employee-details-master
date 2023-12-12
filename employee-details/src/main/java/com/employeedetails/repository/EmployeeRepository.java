package com.employeedetails.repository;

import com.employeedetails.dto.EmployeeDTO;
import com.employeedetails.model.Employee;
import com.employeedetails.model.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {
    @Query("select new com.employeedetails.dto.EmployeeDTO(e) from Employee e")
    List<EmployeeDTO> findAllByStatus(Status status);
}