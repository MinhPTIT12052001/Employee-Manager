package com.globits.da.domain;

import com.globits.core.domain.BaseObject;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tbl_certificate_map")
public class CertificateMap extends BaseObject {
    private static final long serialVersionUID = 1L;
    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;
    @ManyToOne
    @JoinColumn(name = "certificate_id", nullable = false)
    private Certificate certificate;
    @ManyToOne
    @JoinColumn(name = "province_id", nullable = false)
    private Province province;
}
