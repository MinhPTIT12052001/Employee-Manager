package com.globits.da.service;

import com.globits.da.dto.ProvinceDto;

import java.util.List;
import java.util.UUID;

public interface ProvinceService {
    void checkExistById(UUID id);
    ProvinceDto getByID(UUID id);
    List<ProvinceDto> getAll();
    ProvinceDto add(ProvinceDto dto);
    ProvinceDto update(UUID id, ProvinceDto dto);
    boolean delete(UUID id);
}
