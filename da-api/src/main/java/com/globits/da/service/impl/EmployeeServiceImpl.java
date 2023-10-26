package com.globits.da.service.impl;

import com.globits.da.commons.ApiDataError;
import com.globits.da.constants.Const;
import com.globits.da.constants.MessageConst;
import com.globits.da.domain.Employee;
import com.globits.da.dto.EmployeeDto;
import com.globits.da.exception.InvalidApiDataException;
import com.globits.da.exception.InvalidDtoException;
import com.globits.da.repository.EmployeeRepository;
import com.globits.da.service.EmployeeService;
import com.globits.da.utils.ConvertUtils;
import com.globits.da.utils.HandlerExportExcelFile;
import com.globits.da.validation.ValidationEmployee;
import com.globits.da.validator.maker.OnCreate;
import com.globits.da.validator.maker.OnUpdate;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    ConvertUtils convertUtils;
    @Autowired
    HandlerExportExcelFile handlerExportExcelFile;
    @Autowired
    ValidationEmployee validationEmployee;
    @Override
    public List<EmployeeDto> getAllEmployee() {
     return employeeRepository.getAllEmployee();
    }

    @Override
    public EmployeeDto save(EmployeeDto employeeDto) {
        if (ObjectUtils.isEmpty(employeeDto)){
            throw new RuntimeException();
        }
        validationEmployee.checkValidEmployee(employeeDto, OnCreate.class);
        Employee employee = new Employee();
        convertUtils.setEmployeeValue(employee,employeeDto, OnCreate.class);
        employee = employeeRepository.save(employee);
        return new EmployeeDto(employee);
    }
    @Override
    public EmployeeDto getEmployeeById(UUID id) {
        checkExistById(id);
        return new EmployeeDto(employeeRepository.getOne(id));
    }
    @Override
    public EmployeeDto updateEmployee(UUID id, EmployeeDto employeeDto) {
        if (ObjectUtils.isEmpty(employeeDto)){
            throw new InvalidDtoException();
        }
        checkExistById(id);
        validationEmployee.checkValidEmployee(employeeDto, OnUpdate.class);
        Employee employee = employeeRepository.getOne(id);
        convertUtils.setEmployeeValue(employee,employeeDto, OnUpdate.class);
        employee = employeeRepository.save(employee);
        return new EmployeeDto(employee);
    }
    @Override
    public Boolean deleteEmployee(UUID id) {
        checkExistById(id);
        employeeRepository.deleteById(id);
        return true;
    }

    @Override
    public Workbook exportExcel() {
        return handlerExportExcelFile.writeToExcelFile(employeeRepository.getAllEmployee());
    }
    public void checkExistById(UUID id){
        if (ObjectUtils.isEmpty(id) && !employeeRepository.existsById(id)){
            ApiDataError apiDataError = ApiDataError.builder()
                    .field(Const.EMPLOYEE_CONST.FIELD_ID)
                    .data(id)
                    .message(MessageConst.EMPLOYEE_MESSAGE_ERROR.ID_NOT_EXIST)
                    .build();
            throw new InvalidApiDataException(Collections.singletonList(apiDataError));
        }
    }
}
