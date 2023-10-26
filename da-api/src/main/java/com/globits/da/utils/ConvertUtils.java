package com.globits.da.utils;

import com.globits.da.domain.*;
import com.globits.da.dto.*;
import com.globits.da.repository.*;
import com.globits.da.validator.maker.OnCreate;
import com.globits.da.validator.maker.OnUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

@Component
public class ConvertUtils {
    @Autowired
    DistrictRepository districtRepository;
    @Autowired
    ProvinceRepository provinceRepository;
    @Autowired
    TownRepository townRepository;
    @Autowired
    CertificateRepository certificateRepository;
    @Autowired
    EmployeeRepository employeeRepository;
    public void setProvinceValue(Province province, ProvinceDto provinceDto){
        province.setName(provinceDto.getName());
    }
    public void setEmployeeValue(Employee employee, EmployeeDto employeeDto , Class<?> group){
        employee.setName(employeeDto.getName());
        employee.setCode(employeeDto.getCode());
        employee.setPhone(employeeDto.getPhone());
        employee.setEmail(employeeDto.getEmail());
        employee.setAge(employeeDto.getAge());
        if (group.equals(OnCreate.class) || (group.equals(OnUpdate.class)
                && !ObjectUtils.isEmpty(employeeDto.getDistrictId())
                && !ObjectUtils.isEmpty(employeeDto.getProvinceId())
                && !ObjectUtils.isEmpty(employeeDto.getTownId()))){
            employee.setDistrict(districtRepository.getOne(employeeDto.getDistrictId()));
            employee.setProvince(provinceRepository.getOne(employeeDto.getProvinceId()));
            employee.setTown(townRepository.getOne(employeeDto.getTownId()));
        }
    }
    public void setDistrictValue(District district, DistrictDto districtDto, Province province){
        district.setName(districtDto.getName());
        if (!ObjectUtils.isEmpty(province)){
            district.setProvince(province);
        }
    }
    public void setTownValue(Town town, TownDto townDto, District district){
        town.setName(townDto.getName());
        if (!ObjectUtils.isEmpty(district)){
            town.setDistrict(district);
        }
    }
    public void setCertificateValue(Certificate certificate, CertificateDto certificateDto) {
        certificate.setName(certificateDto.getName());
        certificate.setBeginDate(certificateDto.getBeginDate());
        certificate.setExpireDate(certificateDto.getExpireDate());
    }

    public void setCertificateMapValue(CertificateMap certificateMap, CertificateMapDto certificateMapDto) {
        certificateMap.setCertificate(certificateRepository.getOne(certificateMapDto.getCertificateId()));
        certificateMap.setEmployee(employeeRepository.getOne(certificateMapDto.getEmployeeId()));
        certificateMap.setProvince(provinceRepository.getOne(certificateMapDto.getProvinceId()));
    }

}
