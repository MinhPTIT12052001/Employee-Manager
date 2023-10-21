package com.globits.da.service;

import com.globits.da.domain.Certificate;
import com.globits.da.dto.CertificateDto;

import java.util.List;
import java.util.UUID;

public interface CertificateService {
    void checkExistById(UUID id);
    CertificateDto getById(UUID id);
    List<CertificateDto> getAll();
    CertificateDto add(CertificateDto dto);
    CertificateDto update(UUID id, CertificateDto dto);
    Boolean delete(UUID id);
}
