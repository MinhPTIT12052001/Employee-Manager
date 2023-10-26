package com.globits.da.service.impl;

import com.globits.da.commons.ApiDataError;
import com.globits.da.constants.Const;
import com.globits.da.constants.MessageConst;
import com.globits.da.domain.Certificate;
import com.globits.da.dto.CertificateDto;
import com.globits.da.exception.DataNotFoundException;
import com.globits.da.exception.InvalidDtoException;
import com.globits.da.repository.CertificateRepository;
import com.globits.da.service.CertificateService;
import com.globits.da.utils.ConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.UUID;

@Service
public class CertificateServiceImpl implements CertificateService {
    @Autowired
    CertificateRepository certificateRepository;
    @Autowired
    ConvertUtils convertUtils;
    @Override
    public void checkExistById(UUID id) {
        if (!certificateRepository.existsById(id)){
            ApiDataError apiDataError = ApiDataError.builder()
                    .field(Const.CERTIFICATE_CONST.FIELD_ID)
                    .data(id)
                    .message(MessageConst.CERTIFICATE_MESSAGE_ERROR.NOT_FOUND_ID)
                    .build();
            throw new DataNotFoundException(apiDataError);
        }
    }
    @Override
    public CertificateDto getById(UUID id) {
        checkExistById(id);
        return new CertificateDto(certificateRepository.getOne(id));
    }

    @Override
    public List<CertificateDto> getAll() {
        return certificateRepository.getAll();
    }

    @Override
    public CertificateDto add(CertificateDto dto) {
        if (ObjectUtils.isEmpty(dto)){
            throw new InvalidDtoException();
        }
        Certificate certificate = new Certificate();
        convertUtils.setCertificateValue(certificate,dto);
        certificate = certificateRepository.save(certificate);
        return new CertificateDto(certificate);
    }

    @Override
    public CertificateDto update(UUID id, CertificateDto dto) {
        if (ObjectUtils.isEmpty(dto)){
            throw new InvalidDtoException();
        }
        checkExistById(id);
        Certificate certificate = certificateRepository.getOne(id);
        convertUtils.setCertificateValue(certificate,dto);
        certificate = certificateRepository.save(certificate);
        return new CertificateDto(certificate);
    }

    @Override
    public Boolean delete(UUID id) {
        checkExistById(id);
        certificateRepository.deleteById(id);
        return true;
    }
}
