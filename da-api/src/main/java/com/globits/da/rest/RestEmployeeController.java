package com.globits.da.rest;


import com.globits.da.dto.EmployeeDto;
import com.globits.da.service.EmployeeService;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/employee")
public class RestEmployeeController {
    @Autowired
    public EmployeeService employeeService;

    @RequestMapping(path = "/getAllEmployee",method = RequestMethod.GET)
    public ResponseEntity<List<EmployeeDto>> getAllEmployee(){
        List<EmployeeDto> result =employeeService.getAllEmployee();
        return new ResponseEntity<List<EmployeeDto>>(result, HttpStatus.OK);
    }
    @RequestMapping(path = "/add",method = RequestMethod.POST)
    public ResponseEntity<EmployeeDto> add(@RequestBody EmployeeDto employeeDto){
        return new ResponseEntity<>(employeeService.save(employeeDto),HttpStatus.OK);
    }
    @RequestMapping(path = "/{id}",method = RequestMethod.GET)
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable UUID id){
        EmployeeDto employeeDto = employeeService.getEmployeeById(id);
            return new ResponseEntity<>(employeeDto,HttpStatus.OK);
    }
    @RequestMapping(path = "/update/{id}",method = RequestMethod.PUT)
    public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable UUID id, @RequestBody EmployeeDto employeeDto){
        EmployeeDto updateEmployee = employeeService.updateEmployee(id,employeeDto);
            return new ResponseEntity<>(updateEmployee,HttpStatus.OK);
    }
    @RequestMapping(path = "/delete/{id}",method = RequestMethod.DELETE)
    public ResponseEntity<Boolean> deleteEmployee(@PathVariable UUID id){
        Boolean result = employeeService.deleteEmployee(id);
        return ResponseEntity.ok().body(result);
    }
    @RequestMapping(path = "/export", method = RequestMethod.GET)
    public void exportExcel(HttpServletResponse response) throws IOException{
        Workbook workbook = employeeService.exportExcel();
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=employees.xlsx");
        workbook.write(response.getOutputStream());
        workbook.close();
    }


}
