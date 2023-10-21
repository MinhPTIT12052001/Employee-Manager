package com.globits.da.service.impl;

import com.globits.da.commons.ApiDataError;
import com.globits.da.constants.Const;
import com.globits.da.constants.MessageConst;
import com.globits.da.domain.District;
import com.globits.da.domain.Town;
import com.globits.da.dto.TownDto;
import com.globits.da.exception.InvalidApiDataException;
import com.globits.da.exception.InvalidDtoException;
import com.globits.da.repository.DistrictRepository;
import com.globits.da.repository.TownRepository;
import com.globits.da.service.TownService;
import com.globits.da.utils.ConvertUtils;
import com.globits.da.validation.ValidationTown;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
public class TownServiceImpl implements TownService {

    @Autowired
    TownRepository townRepository;
    @Autowired
    ValidationTown validationTown;
    @Autowired
    ConvertUtils convertUtils;
    @Autowired
    DistrictRepository districtRepository;
    @Override
    public void checkExistById(UUID id) {
        if (!townRepository.existsById(id)){
            ApiDataError apiDataError = ApiDataError.builder()
                    .field(Const.TOWN_CONST.FIELD_ID)
                    .data(id)
                    .message(MessageConst.TOWN_MESSAGE_ERROR.ID_NOT_EXIST)
                    .build();
            throw new InvalidApiDataException(Collections.singletonList(apiDataError));
        }
    }
    @Override
    public TownDto getByID(UUID id) {
        checkExistById(id);
        return new TownDto(townRepository.getOne(id));
    }
    @Override
    public List<TownDto> getAll() {
        return townRepository.getAllTown();
    }

    @Override
    public TownDto add(TownDto dto) {
        if (ObjectUtils.isEmpty(dto)){
            throw new InvalidDtoException();
        }
        validationTown.checkValidTown(dto);
        Town town = new Town();
        District district = districtRepository.getOne(dto.getDistrictId());
        convertUtils.setTownValue(town,dto,district);
        town = townRepository.save(town);
        return new TownDto(town);
    }

    @Override
    public TownDto update(UUID id, TownDto dto) {
        if (ObjectUtils.isEmpty(dto)){
            throw new InvalidDtoException();
        }
        checkExistById(id);
        validationTown.checkValidTown(dto);
        Town town = townRepository.getOne(id);
        District district = !ObjectUtils.isEmpty(dto.getDistrictId())
                ? districtRepository.getOne(dto.getDistrictId())
                : null;
        convertUtils.setTownValue(town,dto,district);
        town = townRepository.save(town);
        return new TownDto(town);
    }

    @Override
    public boolean delete(UUID id) {
        checkExistById(id);
        townRepository.deleteById(id);
        return true;
    }

    @Override
    public List<TownDto> findByIdDistrict(UUID districtId) {
        return townRepository.findByIdDistrict(districtId);
    }
}
