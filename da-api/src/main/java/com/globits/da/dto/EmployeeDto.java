package com.globits.da.dto;

import com.globits.core.domain.BaseObject;
import com.globits.da.domain.Employee;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.util.ObjectUtils;

import java.util.UUID;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EmployeeDto extends BaseObject{

    private String code;
    private String name;
    private String email;
    private String phone;
    private Integer age;
//    private UUID provinceId;
//    private UUID districtId;
//    private UUID townId;

    public EmployeeDto(Employee employee){
        if (!ObjectUtils.isEmpty(employee)){
            this.setId(employee.getId());
            this.code = employee.getCode();
            this.name = employee.getName();
            this.email = employee.getEmail();
            this.phone = employee.getPhone();
            this.age = employee.getAge();
//            this.districtId = employee.getDistrict().getId();
//            this.provinceId = employee.getProvince().getId();
//            this.townId = employee.getTown().getId();
        }
    }
}
