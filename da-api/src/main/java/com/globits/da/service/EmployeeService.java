package com.globits.da.service;

import com.globits.da.domain.Employee;
import com.globits.da.dto.EmployeeDto;
import org.apache.poi.ss.usermodel.Workbook;

import java.util.List;
import java.util.UUID;

public interface EmployeeService  {
    public List<EmployeeDto> getAllEmployee();
    public EmployeeDto save(EmployeeDto employeeDto);
    public EmployeeDto getEmployeeById(UUID id);
    public EmployeeDto updateEmployee(UUID id, EmployeeDto employeeDto);
    public Boolean deleteEmployee(UUID id);
    Workbook exportExcel();
}
