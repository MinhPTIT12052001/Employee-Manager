package com.globits.da.dto;

import com.globits.core.domain.BaseObject;
import com.globits.da.domain.District;
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
@Setter
@Getter
public class DistrictDto extends BaseObject {
    private String name;
    private UUID provinceId;
    private List<TownDto> townDtoList;
    public DistrictDto(District district){
        if (!ObjectUtils.isEmpty(district)){
            this.setId(district.getId());
            this.name = district.getName();
            this.provinceId = district.getProvince().getId();
            if (!ObjectUtils.isEmpty(district.getTownList())){
                this.townDtoList = district.getTownList().stream().map(TownDto::new).collect(Collectors.toList());
            }
            else {
                this.townDtoList = new ArrayList<>();
            }
        }
    }
}
