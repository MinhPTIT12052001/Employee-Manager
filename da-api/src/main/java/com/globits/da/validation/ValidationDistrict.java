package com.globits.da.validation;

import com.globits.da.commons.ApiDataError;
import com.globits.da.constants.Const;
import com.globits.da.constants.MessageConst;
import com.globits.da.dto.DistrictDto;
import com.globits.da.exception.InvalidApiDataException;
import com.globits.da.repository.DistrictRepository;
import com.globits.da.repository.ProvinceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.Collections;
import java.util.UUID;

@Component
public class ValidationDistrict {
    @Autowired
    DistrictRepository districtRepository;
    @Autowired
    ProvinceRepository provinceRepository;
    public void checkNameEmpty(String districtName){
        if (ObjectUtils.isEmpty(districtName)){
            ApiDataError apiDataError = ApiDataError.builder()
                    .field(Const.DISTRICT_CONST.FIELD_NAME)
                    .data(districtName)
                    .message(MessageConst.DISTRICT_MESSAGE_ERROR.NAME_NOT_EMPTY)
                    .build();
            throw new InvalidApiDataException(Collections.singletonList(apiDataError));
        }
    }
    public void checkNameExist(String districtName,UUID provinceId ){
        if (districtRepository.isExistByName(districtName,provinceId)){
            ApiDataError apiDataError = ApiDataError.builder()
                    .field(Const.DISTRICT_CONST.FIELD_NAME)
                    .data(districtName)
                    .message(MessageConst.DISTRICT_MESSAGE_ERROR.NAME_IS_EXIST)
                    .build();
            throw new InvalidApiDataException(Collections.singletonList(apiDataError));
        }
    }
    public void checkProvinceValid(UUID provinceId) {
        if(!provinceRepository.existsById(provinceId)) {
            ApiDataError apiDataError = ApiDataError.builder()
                    .field(Const.DISTRICT_CONST.FIELD_PROVINCE)
                    .data(provinceId)
                    .message(MessageConst.PROVINCE_MESSAGE_ERROR.ID_NOT_EXIST)
                    .build();
            throw new InvalidApiDataException(Collections.singletonList(apiDataError));
        }
    }
    public void checkValidDistrict(DistrictDto districtDto){
        checkNameEmpty(districtDto.getName());
        checkNameExist(districtDto.getName(),districtDto.getProvinceId());
        if(!ObjectUtils.isEmpty(districtDto.getProvinceId())) {
            checkProvinceValid(districtDto.getProvinceId());
        }
    }
}
