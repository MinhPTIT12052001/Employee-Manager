package com.globits.da.domain;

import com.globits.core.domain.BaseObject;
import com.globits.da.dto.EmployeeDto;
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
@Table(name = "tbl_town")
public class Town extends BaseObject {
    private static final long serialVersionUID = 1L;

    @Column(name = "name")
    private String name;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "district_id")
    private District district;
    @OneToMany(mappedBy = "town",cascade = CascadeType.ALL)
    private List<Employee> employeeList;
}
