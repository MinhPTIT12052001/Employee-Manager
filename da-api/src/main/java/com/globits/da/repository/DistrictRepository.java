package com.globits.da.repository;

import com.globits.da.domain.District;
import com.globits.da.dto.DistrictDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface DistrictRepository extends JpaRepository<District, UUID> {
    @Query("select new com.globits.da.dto.DistrictDto(d) from District d")
    List<DistrictDto> getAllDistrict();
    @Query("SELECT (count(entity) > 0) FROM District entity " +
            "WHERE lower(entity.name) = lower( :districtName ) " +
            "AND entity.province.id = :provinceId ")
    Boolean isExistByName(@Param("districtName")String districtName,
                          @Param("provinceId") UUID provinceId);
    @Query("select new com.globits.da.dto.DistrictDto(d) from District d where d.province.id = ?1")
    List<DistrictDto> findByIdProvince(UUID provinceId);
    @Query("select (count(d) > 0) from District d where d.id = :districtId and d.province.id = :provinceId")
    Boolean isDistrictInProvince(@Param("districtId") UUID districtId, @Param("provinceId") UUID provinceId);
}
