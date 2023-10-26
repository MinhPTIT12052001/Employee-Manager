package com.globits.da.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.globits.core.domain.BaseObject;
import com.globits.da.constants.MessageConst;
import com.globits.da.constants.RegexConst;
import com.globits.da.domain.Certificate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.util.ObjectUtils;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CertificateDto extends BaseObject {
    @NotBlank(message = MessageConst.CERTIFICATE_MESSAGE_ERROR.NAME_NOT_EMPTY)
    private String name;
    @NotNull(message = MessageConst.CERTIFICATE_MESSAGE_ERROR.BEGIN_DATE_NOT_EMPTY)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = RegexConst.DATE_REGEX)
    private LocalDateTime beginDate;
    @NotNull(message = MessageConst.CERTIFICATE_MESSAGE_ERROR.EXPIRE_DATE_NOT_EMPTY)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = RegexConst.DATE_REGEX)
    private LocalDateTime expireDate;


    public CertificateDto(Certificate certificate){
        if (!ObjectUtils.isEmpty(certificate)){
            this.setId(certificate.getId());
            this.name = certificate.getName();
            this.beginDate = certificate.getBeginDate();
            this.expireDate = certificate.getExpireDate();
    }
}
}
