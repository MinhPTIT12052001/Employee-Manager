package com.globits.da.validation;

import com.globits.da.commons.ApiDataError;
import com.globits.da.constants.Const;
import com.globits.da.constants.MessageConst;
import com.globits.da.dto.CertificateMapDto;
import com.globits.da.exception.DataNotFoundException;
import com.globits.da.exception.InvalidApiDataException;
import com.globits.da.repository.CertificateMapRepository;
import com.globits.da.repository.CertificateRepository;
import com.globits.da.repository.EmployeeRepository;
import com.globits.da.repository.ProvinceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.Collections;
import java.util.UUID;
@Component
public class ValidationCertificateMap {
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    ProvinceRepository provinceRepository;
    @Autowired
    CertificateRepository certificateRepository;
    @Autowired
    CertificateMapRepository certificateMapRepository;
    private void checkEmployeeIdValid(UUID id) {
        if(!ObjectUtils.isEmpty(id) && !employeeRepository.existsById(id)) {
            ApiDataError apiDataError = ApiDataError.builder()
                    .field(Const.CERTIFICATE_MAPPING_CONST.FIELD_EMPLOYEE)
                    .data(id)
                    .message(MessageConst.EMPLOYEE_MESSAGE_ERROR.ID_NOT_EXIST)
                    .build();
            throw new DataNotFoundException(apiDataError);
        }
    }
    private void checkCertificateIdValid(UUID id) {
        if(!ObjectUtils.isEmpty(id) && !certificateRepository.existsById(id)) {
            ApiDataError apiDataError = ApiDataError.builder()
                    .field(Const.CERTIFICATE_MAPPING_CONST.FIELD_CERTIFICATE)
                    .data(id)
                    .message(MessageConst.CERTIFICATE_MESSAGE_ERROR.NOT_FOUND_ID)
                    .build();
            throw new DataNotFoundException(apiDataError);
        }
    }
    private void checkProvinceIdValid(UUID id) {
        if(!ObjectUtils.isEmpty(id) && !provinceRepository.existsById(id)) {
            ApiDataError apiDataError = ApiDataError.builder()
                    .field(Const.CERTIFICATE_MAPPING_CONST.FIELD_PROVINCE)
                    .data(id)
                    .message(MessageConst.PROVINCE_MESSAGE_ERROR.ID_NOT_EXIST)
                    .build();
            throw new DataNotFoundException(apiDataError);
        }
    }
    private void checkLimitCertificate(UUID employeeId, UUID certificateId) throws InvalidApiDataException {
        int numOfCertificateInUse =
                certificateMapRepository.countCertificateInUse(employeeId, certificateId);
        if(numOfCertificateInUse >= Const.CERTIFICATE_MAPPING_CONST.NUMBER_OF_CERTIFICATE_LIMIT) {
            ApiDataError apiDataError = ApiDataError.builder()
                    .field(Const.CERTIFICATE_MAPPING_CONST.FIELD_CERTIFICATE)
                    .message(MessageConst.CERTIFICATE_SAME_TYPE_ERROR)
                    .build();
            throw new InvalidApiDataException(Collections.singletonList(apiDataError));
        }
    }
    private void checkTypeCertificate(UUID employeeId, UUID certificateId, UUID provinceId) {
        if(!ObjectUtils.isEmpty(certificateMapRepository
                .getCertificateInUseByProvinceId(employeeId,
                        certificateId,
                        provinceId))) {
            ApiDataError apiDataError = ApiDataError.builder()
                    .field(Const.CERTIFICATE_MAPPING_CONST.FIELD_CERTIFICATE)
                    .message(MessageConst.CERTIFICATE_LIMIT_ERROR)
                    .build();
            throw new DataNotFoundException(apiDataError);
        }
    }
    public void checkValidCertificateMap(CertificateMapDto dto) throws InvalidApiDataException {
        checkEmployeeIdValid(dto.getEmployeeId());
        checkProvinceIdValid(dto.getProvinceId());
        checkCertificateIdValid(dto.getCertificateId());
        checkLimitCertificate(dto.getEmployeeId(), dto.getCertificateId());
        checkTypeCertificate(dto.getEmployeeId(), dto.getCertificateId(), dto.getProvinceId());
    }
}
