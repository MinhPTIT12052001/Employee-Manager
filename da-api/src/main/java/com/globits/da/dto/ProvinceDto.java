package com.globits.da.dto;

import com.globits.core.domain.BaseObject;
import com.globits.da.domain.District;
import com.globits.da.domain.Province;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProvinceDto extends BaseObject {

    private String name;
    private List<DistrictDto> districtDtoList;
    public ProvinceDto(Province province){
        if (!ObjectUtils.isEmpty(province)){
            this.setId(province.getId());
            this.name = province.getName();
            if (!ObjectUtils.isEmpty(province.getDistrictList())){
                this.districtDtoList = province.getDistrictList().stream().map(DistrictDto::new).collect(Collectors.toList());
            }
            else{
                this.districtDtoList = new ArrayList<>();
            }
        }
    }
}
