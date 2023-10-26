package com.globits.da.dto;

import com.globits.core.domain.BaseObject;
import com.globits.da.constants.MessageConst;
import com.globits.da.domain.CertificateMap;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.util.ObjectUtils;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CertificateMapDto extends BaseObject {
    @NotNull(message = MessageConst.CERTIFICATE_MAPPING_MESSAGE_ERROR.EMPLOYEE_NOT_EMPTY)
    private UUID employeeId;
    @NotNull(message = MessageConst.CERTIFICATE_MAPPING_MESSAGE_ERROR.PROVINCE_NOT_EMPTY)
    private UUID provinceId;
    @NotNull(message = MessageConst.CERTIFICATE_MAPPING_MESSAGE_ERROR.CERTIFICATE_NOT_EMPTY)
    private UUID certificateId;
    public CertificateMapDto(CertificateMap certificateMap) {
        if(!ObjectUtils.isEmpty(certificateMap)) {
            this.setId(certificateMap.getId());
            this.certificateId = certificateMap.getCertificate().getId();
            this.provinceId = certificateMap.getProvince().getId();
            this.employeeId = certificateMap.getEmployee().getId();
        }
    }
}
