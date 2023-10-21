package com.globits.da.rest;

import com.globits.da.dto.DistrictDto;
import com.globits.da.service.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/district")
public class RestDistrictController {
    @Autowired
    DistrictService districtService;

    @RequestMapping(value = "/getAllDistrict",method = RequestMethod.GET)
    public ResponseEntity<List<DistrictDto>> getAllDistrict(){
        return new ResponseEntity<>(districtService.getAllDistrict(), HttpStatus.OK);
    }
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public ResponseEntity<DistrictDto> addDistrict(@RequestBody DistrictDto districtDto){
        return new ResponseEntity<>(districtService.add(districtDto),HttpStatus.OK);
    }
    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public ResponseEntity<DistrictDto> update(@RequestBody DistrictDto districtDto,@PathVariable UUID id){
        return new ResponseEntity<>(districtService.update(id,districtDto), HttpStatus.OK);
    }
    @RequestMapping(value = "/delete/{id}",method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable UUID id){
        boolean result = districtService.delete(id);
        return new ResponseEntity<>(result ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }
    @RequestMapping(value = "/getById/{id}",method = RequestMethod.GET)
    public ResponseEntity<DistrictDto> getById(@PathVariable UUID id){
        return new ResponseEntity<>(districtService.getById(id),HttpStatus.OK);
    }
    @RequestMapping(value = "/findByIdProvince/{id}",method = RequestMethod.POST)
    public ResponseEntity<List<DistrictDto>> findByIdProvince(@PathVariable UUID id){
        return ResponseEntity.ok().body(districtService.findByIdProvince(id));
    }
}
