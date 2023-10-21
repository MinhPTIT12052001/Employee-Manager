package com.globits.da.rest;

import com.globits.da.dto.ProvinceDto;
import com.globits.da.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/province")
public class RestProvinceController  {
    @Autowired
    ProvinceService provinceService;

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public ResponseEntity<ProvinceDto> addProvince(@RequestBody ProvinceDto dto){
        return new ResponseEntity<>(provinceService.add(dto),HttpStatus.OK );
    }
    @RequestMapping(value = "/getAllProvince",method = RequestMethod.GET)
    public ResponseEntity<List<ProvinceDto>> getAllProvince(){
        return new ResponseEntity<>(provinceService.getAll(),HttpStatus.OK);
    }
    @RequestMapping(value = "/update/{id}",method = RequestMethod.PUT)
    public ResponseEntity<ProvinceDto> updateProvince(@PathVariable UUID id, @RequestBody ProvinceDto dto){
        return new ResponseEntity<>(provinceService.update(id,dto), HttpStatus.OK);
    }
    @RequestMapping(value = "/delete/{id}",method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteProvince(@PathVariable UUID id){
        boolean result = provinceService.delete(id);
        return new ResponseEntity<>(result ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }
    @RequestMapping(value = "/getById/{id}",method = RequestMethod.GET)
    public ResponseEntity<ProvinceDto> getProvinceById(@PathVariable UUID id){
        return new ResponseEntity<>(provinceService.getByID(id),HttpStatus.OK);
    }
}
