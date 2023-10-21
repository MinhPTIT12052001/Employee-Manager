package com.globits.da.dto;

import com.globits.core.domain.BaseObject;
import com.globits.da.domain.Province;
import com.globits.da.domain.Town;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.util.ObjectUtils;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TownDto extends BaseObject {

    private String name;
    private UUID districtId;
    public TownDto(Town town){
        if (!ObjectUtils.isEmpty(town)){
            this.setId(town.getId());
            this.name = town.getName();
            this.districtId = town.getDistrict().getId();
        }
    }
}
