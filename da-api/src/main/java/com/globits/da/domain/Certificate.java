package com.globits.da.domain;


import com.globits.core.domain.BaseObject;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

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
}
