package com.globits.da.domain;

import com.globits.core.domain.BaseObject;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tbl_district")
public class District extends BaseObject {
    private static final long serialVersionUID = 1L;

    @Column(name = "name")
    private String name;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "province_id")
    private Province province;
    @OneToMany(mappedBy = "district", cascade = CascadeType.ALL)
    private List<Town> townList;
    @OneToMany(mappedBy = "district", cascade = CascadeType.ALL)
    private List<Employee> employeeList;
}
