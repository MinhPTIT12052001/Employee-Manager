package com.globits.da.repository;

import com.globits.da.domain.Province;
import com.globits.da.dto.ProvinceDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface ProvinceRepository extends JpaRepository<Province, UUID> {
    @Query("select new com.globits.da.dto.ProvinceDto(p) from Province p")
    List<ProvinceDto> getAll();

    @Query("select (count (p) > 0)  from Province p where lower(p.name) = lower(:provinceName) ")
    Boolean isExistByName(@Param("provinceName") String provinceName);
}
