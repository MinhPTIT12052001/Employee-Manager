package com.globits.da.service.impl;

import com.globits.da.commons.ApiDataError;
import com.globits.da.constants.Const;
import com.globits.da.constants.MessageConst;
import com.globits.da.domain.CertificateMap;
import com.globits.da.dto.CertificateMapDto;
import com.globits.da.exception.DataNotFoundException;
import com.globits.da.exception.InvalidDtoException;
import com.globits.da.repository.CertificateMapRepository;
import com.globits.da.service.CertificateMapService;
import com.globits.da.utils.ConvertUtils;
import com.globits.da.validation.ValidationCertificateMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.UUID;
@Service
public class CertificateMapServiceImpl implements CertificateMapService {
    @Autowired
    CertificateMapRepository certificateMapRepository;
    @Autowired
    ValidationCertificateMap validationCertificateMap;
    @Autowired
    ConvertUtils convertUtils;

    @Override
    public void checkExistById(UUID id) {
        if(!certificateMapRepository.existsById(id)) {
            ApiDataError apiDataError = ApiDataError.builder()
                    .field(Const.CERTIFICATE_MAPPING_CONST.FIELD_ID)
                    .data(id)
                    .message(MessageConst.CERTIFICATE_MAPPING_MESSAGE_ERROR.NOT_FOUND_ID)
                    .build();
            throw new DataNotFoundException(apiDataError);
        }
    }
    @Override
    public List<CertificateMapDto> getAll() {
        return certificateMapRepository.getAll();
    }

    @Override
    public List<CertificateMapDto> getByEmployeeId(UUID id) {
        return certificateMapRepository.getByEmployeeId(id);
    }

    @Override
    public CertificateMapDto save(CertificateMapDto dto){
        if(ObjectUtils.isEmpty(dto)) {
            throw new InvalidDtoException();
        }
        CertificateMap certificateMap = new CertificateMap();
        validationCertificateMap.checkValidCertificateMap(dto);
        convertUtils.setCertificateMapValue(certificateMap, dto);
        certificateMap = certificateMapRepository.save(certificateMap);
        return new CertificateMapDto(certificateMap);
    }

    @Override
    public CertificateMapDto update(CertificateMapDto dto, UUID id){
        if(ObjectUtils.isEmpty(dto)) {
            throw new InvalidDtoException();
        }
        checkExistById(id);
        validationCertificateMap.checkValidCertificateMap(dto);
        CertificateMap certificateMap = certificateMapRepository.getOne(id);
        convertUtils.setCertificateMapValue(certificateMap, dto);
        certificateMap = certificateMapRepository.save(certificateMap);
        return new CertificateMapDto(certificateMap);
    }

    @Override
    public Boolean deleteById(UUID id) {
        checkExistById(id);
        certificateMapRepository.deleteById(id);
        return true;
    }
}
