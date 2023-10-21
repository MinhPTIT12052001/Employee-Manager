package com.globits.da.service.impl;

import com.globits.da.commons.ApiDataError;
import com.globits.da.constants.Const;
import com.globits.da.constants.MessageConst;
import com.globits.da.domain.Province;
import com.globits.da.dto.ProvinceDto;
import com.globits.da.exception.InvalidApiDataException;
import com.globits.da.exception.InvalidDtoException;
import com.globits.da.repository.ProvinceRepository;
import com.globits.da.service.ProvinceService;
import com.globits.da.utils.ConvertUtils;
import com.globits.da.validation.ValidationProvince;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
public class ProvinceServiceImpl implements ProvinceService {

    @Autowired
    ProvinceRepository provinceRepository;
    @Autowired
    ValidationProvince validationProvince;
    @Autowired
    ConvertUtils convertUtils;

    @Override
    public void checkExistById(UUID id) {
        if (!provinceRepository.existsById(id)){
            ApiDataError apiDataError = ApiDataError.builder()
                    .field(Const.PROVINCE_CONST.FIELD_ID)
                    .data(id)
                    .message(MessageConst.PROVINCE_MESSAGE_ERROR.ID_NOT_EXIST)
                    .build();
            throw new InvalidApiDataException(Collections.singletonList(apiDataError));
        }
    }
    @Override
    public ProvinceDto getByID(UUID id) {
        checkExistById(id);
        return new ProvinceDto(provinceRepository.getOne(id));
    }

    @Override
    public List<ProvinceDto> getAll() {
        return provinceRepository.getAll();
    }

    @Override
    public ProvinceDto add(ProvinceDto dto) {
        if (ObjectUtils.isEmpty(dto)){
            throw new InvalidDtoException();
        }
        validationProvince.checkValidProvince(dto);
        Province province = new Province();
        convertUtils.setProvinceValue(province,dto);
        provinceRepository.save(province);
        return new ProvinceDto(province);
    }

    @Override
    public ProvinceDto update(UUID id, ProvinceDto dto) {
        if (ObjectUtils.isEmpty(dto)){
            throw new InvalidDtoException();
        }
        checkExistById(id);
        validationProvince.checkValidProvince(dto);
        Province province = provinceRepository.getOne(id);
        convertUtils.setProvinceValue(province,dto);
        province = provinceRepository.save(province);
        return new ProvinceDto(province);
    }

    @Override
    public boolean delete(UUID id) {
        checkExistById(id);
        provinceRepository.deleteById(id);
        return true;
    }
}
