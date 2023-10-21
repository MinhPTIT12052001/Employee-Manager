package com.globits.da.service.impl;

import com.globits.da.commons.ApiDataError;
import com.globits.da.constants.Const;
import com.globits.da.constants.MessageConst;
import com.globits.da.domain.District;
import com.globits.da.domain.Province;
import com.globits.da.dto.DistrictDto;
import com.globits.da.exception.InvalidApiDataException;
import com.globits.da.exception.InvalidDtoException;
import com.globits.da.repository.DistrictRepository;
import com.globits.da.repository.ProvinceRepository;
import com.globits.da.service.DistrictService;
import com.globits.da.utils.ConvertUtils;
import com.globits.da.validation.ValidationDistrict;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
public class DistrictServiceImpl implements DistrictService {
    @Autowired
    DistrictRepository districtRepository;
    @Autowired
    ValidationDistrict validationDistrict;
    @Autowired
    ConvertUtils convertUtils;
    @Autowired
    ProvinceRepository provinceRepository;
    @Override
    public List<DistrictDto> getAllDistrict() {
        return districtRepository.getAllDistrict();
    }

    @Override
    public DistrictDto add(DistrictDto districtDto) {
        if (ObjectUtils.isEmpty(districtDto)){
            throw new InvalidDtoException();
        }
        validationDistrict.checkValidDistrict(districtDto);
        District district = new District();
        Province province = provinceRepository.getOne(districtDto.getProvinceId());
        convertUtils.setDistrictValue(district,districtDto,province);
        district = districtRepository.save(district);
        return new DistrictDto(district);
    }

    @Override
    public DistrictDto update(UUID id, DistrictDto districtDto) {
        if (ObjectUtils.isEmpty(districtDto)){
            throw new InvalidDtoException();
        }
        checkExistById(id);
        validationDistrict.checkValidDistrict(districtDto);
        District district = districtRepository.getOne(id);
        Province province = !ObjectUtils.isEmpty(districtDto.getProvinceId())
                ? provinceRepository.getOne(districtDto.getProvinceId())
                : null;
        convertUtils.setDistrictValue(district,districtDto,province);
        district = districtRepository.save(district);
        return new DistrictDto(district);
    }

    @Override
    public boolean delete(UUID id) {
        checkExistById(id);
        districtRepository.deleteById(id);
        return true;
    }

    @Override
    public void checkExistById(UUID id) {
        if (ObjectUtils.isEmpty(id) && !districtRepository.existsById(id)){
            ApiDataError apiDataError = ApiDataError.builder()
                    .field(Const.DISTRICT_CONST.FIELD_ID)
                    .data(id)
                    .message(MessageConst.DISTRICT_MESSAGE_ERROR.ID_NOT_EXIST)
                    .build();
            throw new InvalidApiDataException(Collections.singletonList(apiDataError));
        }
    }

    @Override
    public DistrictDto getById(UUID id) {
        checkExistById(id);
        return new DistrictDto(districtRepository.getOne(id));
    }

    @Override
    public List<DistrictDto> findByIdProvince(UUID provinceId) {
        return districtRepository.findByIdProvince(provinceId);
    }
}
