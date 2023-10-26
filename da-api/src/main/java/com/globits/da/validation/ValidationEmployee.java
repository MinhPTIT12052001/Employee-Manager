package com.globits.da.validation;

import com.globits.da.commons.ApiDataError;
import com.globits.da.constants.Const;
import com.globits.da.constants.MessageConst;
import com.globits.da.constants.RegexConst;
import com.globits.da.dto.EmployeeDto;
import com.globits.da.exception.InvalidApiDataException;
import com.globits.da.repository.DistrictRepository;
import com.globits.da.repository.EmployeeRepository;
import com.globits.da.repository.ProvinceRepository;
import com.globits.da.repository.TownRepository;
import com.globits.da.validator.maker.OnCreate;
import com.globits.da.validator.maker.OnUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.Collections;
import java.util.UUID;

@Component
public class ValidationEmployee {

    @Autowired
    RegexValidator regexValidator;
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    ProvinceRepository provinceRepository;
    @Autowired
    DistrictRepository districtRepository;
    @Autowired
    TownRepository townRepository;
    public void checkCodeValid(String code){
        if (!regexValidator.isMatches(RegexConst.CODE_REGEX,code)){
            ApiDataError apiDataError = ApiDataError.builder()
                    .field(Const.EMPLOYEE_CONST.FIELD_CODE)
                    .data(code)
                    .message(MessageConst.EMPLOYEE_MESSAGE_ERROR.CODE_INVALID)
                    .build();
            throw new InvalidApiDataException(Collections.singletonList(apiDataError));
        }
        if (employeeRepository.isExistByCode(code)){
            ApiDataError apiDataError = ApiDataError.builder()
                    .field(Const.EMPLOYEE_CONST.FIELD_CODE)
                    .data(code)
                    .message(MessageConst.EMPLOYEE_MESSAGE_ERROR.CODE_IS_EXIST)
                    .build();
            throw new InvalidApiDataException(Collections.singletonList(apiDataError));
        }
    }
    public void checkEmailValid(String email){
        if (!regexValidator.isMatches(RegexConst.EMAIL_REGEX,email)){
            ApiDataError apiDataError = ApiDataError.builder()
                    .field(Const.EMPLOYEE_CONST.FIELD_EMAIL)
                    .data(email)
                    .message(MessageConst.EMPLOYEE_MESSAGE_ERROR.EMAIL_INVALID)
                    .build();
            throw new InvalidApiDataException(Collections.singletonList(apiDataError));
        }
    }
    public void checkNameValid(String name){
        if (ObjectUtils.isEmpty(name)){
            ApiDataError apiDataError = ApiDataError.builder()
                    .field(Const.EMPLOYEE_CONST.FIELD_NAME)
                    .data(name)
                    .message(MessageConst.EMPLOYEE_MESSAGE_ERROR.NAME_NOT_EMPTY)
                    .build();
            throw new InvalidApiDataException(Collections.singletonList(apiDataError));
        }
    }
    public void checkPhoneValid(String phone){
        if (!regexValidator.isMatches(RegexConst.PHONE_REGEX,phone)){
            ApiDataError apiDataError = ApiDataError.builder()
                    .field(Const.EMPLOYEE_CONST.FIELD_PHONE)
                    .data(phone)
                    .message(MessageConst.EMPLOYEE_MESSAGE_ERROR.PHONE_INVALID)
                    .build();
            throw new InvalidApiDataException(Collections.singletonList(apiDataError));
        }
    }
    public void checkAgeValid(Integer age){
        if (age <= 0){
            ApiDataError apiDataError = ApiDataError.builder()
                    .field(Const.EMPLOYEE_CONST.FIELD_AGE)
                    .data(age)
                    .message(MessageConst.EMPLOYEE_MESSAGE_ERROR.AGE_INVALID)
                    .build();
            throw new InvalidApiDataException(Collections.singletonList(apiDataError));
        }
    }
    public void checkProvinceIdValid(UUID provinceId){
        if (ObjectUtils.isEmpty(provinceId) || !provinceRepository.existsById(provinceId)){
            ApiDataError apiDataError = ApiDataError.builder()
                    .field(Const.EMPLOYEE_CONST.FIELD_PROVINCE)
                    .data(provinceId)
                    .message(MessageConst.EMPLOYEE_MESSAGE_ERROR.ID_NOT_EXIST)
                    .build();
            throw new InvalidApiDataException(Collections.singletonList(apiDataError));
        }
    }
    public void checkDistrictIdValid(UUID districtId){
        if (ObjectUtils.isEmpty(districtId) || !districtRepository.existsById(districtId)){
            ApiDataError apiDataError = ApiDataError.builder()
                    .field(Const.EMPLOYEE_CONST.FIELD_DISTRICT)
                    .data(districtId)
                    .message(MessageConst.EMPLOYEE_MESSAGE_ERROR.ID_NOT_EXIST)
                    .build();
            throw new InvalidApiDataException(Collections.singletonList(apiDataError));
        }
    }
    public void checkTownIdValid(UUID townId){
        if (ObjectUtils.isEmpty(townId) || !townRepository.existsById(townId)){
            ApiDataError apiDataError = ApiDataError.builder()
                    .field(Const.EMPLOYEE_CONST.FIELD_TOWN)
                    .data(townId)
                    .message(MessageConst.EMPLOYEE_MESSAGE_ERROR.ID_NOT_EXIST)
                    .build();
            throw new InvalidApiDataException(Collections.singletonList(apiDataError));
        }
    }
    private void checkDistrictBelongToProvince(UUID provinceId, UUID districtId) throws InvalidApiDataException{
        if (!districtRepository.isDistrictInProvince(districtId,provinceId)){
            ApiDataError apiDataError = ApiDataError.builder()
                    .field(Const.EMPLOYEE_CONST.FIELD_DISTRICT)
                    .message(MessageConst.EMPLOYEE_MESSAGE_ERROR.DISTRICT_NOT_BELONG_TO_PROVINCE)
                    .build();
            throw new InvalidApiDataException(Collections.singletonList(apiDataError));
        }
    }
    private void checkTownBeLongToDistrict(UUID townId, UUID districtId) throws InvalidApiDataException{
        if (!townRepository.isTownInDistrict(townId,districtId)){
            ApiDataError apiDataError = ApiDataError.builder()
                    .field(Const.EMPLOYEE_CONST.FIELD_TOWN)
                    .message(MessageConst.EMPLOYEE_MESSAGE_ERROR.TOWN_NOT_BELONG_TO_DISTRICT)
                    .build();
            throw new InvalidApiDataException(Collections.singletonList(apiDataError));
        }
    }
    public void checkValidEmployee(EmployeeDto employeeDto,Class<?> group) throws InvalidApiDataException{
        checkCodeValid(employeeDto.getCode());
        checkNameValid(employeeDto.getName());
        checkPhoneValid(employeeDto.getPhone());
        checkAgeValid(employeeDto.getAge());
        checkEmailValid(employeeDto.getEmail());
        if(group.equals(OnCreate.class) ||
                (group.equals(OnUpdate.class)
                        && !ObjectUtils.isEmpty(employeeDto.getProvinceId())
                        && !ObjectUtils.isEmpty(employeeDto.getDistrictId())
                        && !ObjectUtils.isEmpty(employeeDto.getTownId()))) {
            checkDistrictIdValid(employeeDto.getDistrictId());
            checkProvinceIdValid(employeeDto.getProvinceId());
            checkTownIdValid(employeeDto.getTownId());
            checkDistrictBelongToProvince(employeeDto.getProvinceId(),employeeDto.getDistrictId());
            checkTownBeLongToDistrict(employeeDto.getTownId(),employeeDto.getDistrictId());
        }
    }
}
