package com.globits.da.rest;

import com.globits.da.dto.TownDto;
import com.globits.da.service.TownService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
@RestController
@RequestMapping("/api/town")
public class RestTownController {
    @Autowired
    TownService townService;

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public ResponseEntity<TownDto> addTown(@RequestBody TownDto dto){
        return new ResponseEntity<>(townService.add(dto), HttpStatus.OK );
    }
    @RequestMapping(value = "/getAllTown",method = RequestMethod.GET)
    public ResponseEntity<List<TownDto>> getAllTown(){
        return new ResponseEntity<>(townService.getAll(),HttpStatus.OK);
    }
    @RequestMapping(value = "/update/{id}",method = RequestMethod.PUT)
    public ResponseEntity<TownDto> updateProvince(@PathVariable UUID id, @RequestBody TownDto dto){
        return new ResponseEntity<>(townService.update(id,dto), HttpStatus.OK);
    }
    @RequestMapping(value = "/delete/{id}",method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteTown(@PathVariable UUID id){
        boolean result = townService.delete(id);
        return new ResponseEntity<>(result ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }
    @RequestMapping(value = "/getById/{id}",method = RequestMethod.GET)
    public ResponseEntity<TownDto> getTownById(@PathVariable UUID id){
        return new ResponseEntity<>(townService.getByID(id),HttpStatus.OK);
    }
    @RequestMapping(value = "/findByDistrictId/{id}",method = RequestMethod.POST)
    public ResponseEntity<List<TownDto>> findByDistrictId(@PathVariable UUID id){
        return ResponseEntity.ok().body(townService.findByIdDistrict(id));
    }
}
