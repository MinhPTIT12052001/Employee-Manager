package com.globits.da.service;

import com.globits.da.dto.CertificateMapDto;

import java.util.List;
import java.util.UUID;

public interface CertificateMapService {
    void checkExistById(UUID id);
    List<CertificateMapDto> getAll();
    List<CertificateMapDto> getByEmployeeId(UUID id);
    CertificateMapDto save(CertificateMapDto conferringDto);
    CertificateMapDto update(CertificateMapDto conferringDto, UUID id);
    Boolean deleteById(UUID id);
}
