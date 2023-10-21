package com.globits.da.service;

import com.globits.da.dto.DistrictDto;

import java.util.List;
import java.util.UUID;

public interface DistrictService {
    List<DistrictDto> getAllDistrict();
    DistrictDto add(DistrictDto districtDto);
    DistrictDto update(UUID id, DistrictDto districtDto);
    boolean delete(UUID id);
    void checkExistById(UUID id);
    DistrictDto getById(UUID id);
    List<DistrictDto> findByIdProvince(UUID provinceId);
}
