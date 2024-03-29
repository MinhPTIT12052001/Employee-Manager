package com.globits.da.repository;

import com.globits.da.domain.Employee;
import com.globits.da.dto.EmployeeDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, UUID> {
    @Query("select new com.globits.da.dto.EmployeeDto(e) from Employee e")
    List<EmployeeDto> getAllEmployee();
    @Query("select (count(e) > 0) from Employee e where e.code = :code")
    Boolean isExistByCode(@Param("code") String code);
}
