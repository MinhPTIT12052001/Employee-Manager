package com.globits.da.validation;

import com.globits.da.commons.ApiDataError;
import com.globits.da.constants.Const;
import com.globits.da.constants.MessageConst;
import com.globits.da.dto.ProvinceDto;
import com.globits.da.exception.InvalidApiDataException;
import com.globits.da.exception.InvalidDtoException;
import com.globits.da.repository.ProvinceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.Collections;

@Component
public class ValidationProvince {

    @Autowired
    ProvinceRepository provinceRepository;

    public void checkNameNotEmpty(String provinceName){
        if (ObjectUtils.isEmpty(provinceName)){
            ApiDataError apiDataError = ApiDataError.builder()
                    .field(Const.PROVINCE_CONST.FIELD_NAME)
                    .data(provinceName)
                    .message(MessageConst.PROVINCE_MESSAGE_ERROR.NAME_NOT_EMPTY)
                    .build();
            throw new InvalidApiDataException(Collections.singletonList(apiDataError));
        }
    }
    public void checkExistByName(String provinceName){
        if (provinceRepository.isExistByName(provinceName)){
            ApiDataError apiDataError = ApiDataError.builder()
                    .field(Const.PROVINCE_CONST.FIELD_NAME)
                    .data(provinceName)
                    .message(MessageConst.PROVINCE_MESSAGE_ERROR.NAME_IS_EXIST)
                    .build();
            throw new InvalidApiDataException(Collections.singletonList(apiDataError));
        }
    }
    public void checkValidProvince(ProvinceDto provinceDto){
        checkNameNotEmpty(provinceDto.getName());
        checkExistByName(provinceDto.getName().trim());
    }
}
