package com.globits.da.domain;

import com.globits.core.domain.BaseObject;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tbl_employee")
public class Employee extends BaseObject {
    private static final long serialVersionUID = 1L;

    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "age")
    private Integer age;

    @ManyToOne()
    @JoinColumn(name = "province_id")
    private Province province;

    @ManyToOne()
    @JoinColumn(name = "district_id")
    private District district;
    @ManyToOne()
    @JoinColumn(name = "town_id")
    private Town town;
    @OneToMany(mappedBy = "employee",cascade = CascadeType.ALL)
    private List<CertificateMap> certificateList;
}
