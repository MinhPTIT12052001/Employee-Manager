package com.globits.da.validation;

import com.globits.da.commons.ApiDataError;
import com.globits.da.constants.Const;
import com.globits.da.constants.MessageConst;
import com.globits.da.dto.TownDto;
import com.globits.da.exception.InvalidApiDataException;
import com.globits.da.repository.DistrictRepository;
import com.globits.da.repository.TownRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.Collections;
import java.util.UUID;

@Component
public class ValidationTown {
    @Autowired
    TownRepository townRepository;
    @Autowired
    DistrictRepository districtRepository;

    public void checkNameNotEmpty(String townName){
        if (ObjectUtils.isEmpty(townName)){
            ApiDataError apiDataError = ApiDataError.builder()
                    .field(Const.TOWN_CONST.FIELD_NAME)
                    .data(townName)
                    .message(MessageConst.TOWN_MESSAGE_ERROR.NAME_NOT_EMPTY)
                    .build();
            throw new InvalidApiDataException(Collections.singletonList(apiDataError));
        }
    }
    public void checkExistByName(String townName, UUID districtId){
        if (townRepository.isExistByName(townName,districtId)){
            ApiDataError apiDataError = ApiDataError.builder()
                    .field(Const.TOWN_CONST.FIELD_NAME)
                    .data(townName)
                    .message(MessageConst.TOWN_MESSAGE_ERROR.NAME_IS_EXIST)
                    .build();
            throw new InvalidApiDataException(Collections.singletonList(apiDataError));
        }
    }
    public void checkDistrictValid(UUID districtId){
        if (!districtRepository.existsById(districtId)){
            ApiDataError apiDataError = ApiDataError.builder()
                    .field(Const.TOWN_CONST.FIELD_DISTRICT)
                    .data(districtId)
                    .message(MessageConst.DISTRICT_MESSAGE_ERROR.ID_NOT_EXIST)
                    .build();
            throw new InvalidApiDataException(Collections.singletonList(apiDataError));
        }
    }
    public void checkValidTown(TownDto townDto){
        checkNameNotEmpty(townDto.getName());
        checkExistByName(townDto.getName().trim(),townDto.getDistrictId());
        if (!ObjectUtils.isEmpty(townDto.getDistrictId())){
            checkDistrictValid(townDto.getDistrictId());
        }
    }
}
