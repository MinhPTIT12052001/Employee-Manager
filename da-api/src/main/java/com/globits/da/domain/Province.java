package com.globits.da.domain;

import com.globits.core.domain.BaseObject;
import com.globits.da.dto.EmployeeDto;
import lombok.*;
import org.w3c.dom.stylesheets.LinkStyle;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tbl_province")
public class Province extends BaseObject {
    private static final long serialVersionUID = 1L;

    @Column(name = "name")
    private String name;
    @OneToMany(mappedBy = "province", cascade = CascadeType.ALL)
    private List<District> districtList;
//    @OneToMany(mappedBy = "province", cascade = CascadeType.ALL)
//    private List<Employee> employeeList;
}
