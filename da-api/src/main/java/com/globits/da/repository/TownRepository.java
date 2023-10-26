package com.globits.da.repository;

import com.globits.da.domain.Town;
import com.globits.da.dto.DistrictDto;
import com.globits.da.dto.TownDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TownRepository extends JpaRepository<Town, UUID> {
    @Query("select new com.globits.da.dto.TownDto(t) from Town t")
    List<TownDto> getAllTown();
    @Query("select (count(t) > 0) from Town t " +
            "where lower(t.name) = lower(:townName)" +
            "and t.district.id = :districtId")
    Boolean isExistByName(@Param("townName") String townName,
                          @Param("districtId") UUID districtId);
    @Query("select new com.globits.da.dto.TownDto(t) from Town t where t.district.id = ?1")
    List<TownDto> findByIdDistrict(UUID districtId);

    @Query("select (count (t) > 0) from Town t where t.id = :townId and t.district.id = :districtId")
    Boolean isTownInDistrict(@Param("townId") UUID townId, @Param("districtId") UUID districtId);
}
