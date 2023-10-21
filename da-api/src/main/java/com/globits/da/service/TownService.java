package com.globits.da.service;

import com.globits.da.dto.TownDto;

import java.util.List;
import java.util.UUID;

public interface TownService {
    void checkExistById(UUID id);
    TownDto getByID(UUID id);
    List<TownDto> getAll();
    TownDto add(TownDto dto);
    TownDto update(UUID id, TownDto dto);
    boolean delete(UUID id);
    List<TownDto> findByIdDistrict(UUID districtId);
}
