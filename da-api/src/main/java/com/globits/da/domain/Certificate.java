package com.globits.da.domain;


import com.globits.core.domain.BaseObject;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tbl_certificate")
public class Certificate extends BaseObject {
    private static final long serialVersionUID = 1L;
    @Column(name = "name")
    private String name;
    @Column(name = "begin_date")
    private LocalDateTime beginDate;
    @Column(name = "expire_date")
    private LocalDateTime expireDate;
    @OneToMany(mappedBy = "certificate")
    private List<CertificateMap> certificateMaps;
}
