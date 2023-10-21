package com.globits.da.rest;

import com.globits.da.dto.CertificateDto;
import com.globits.da.service.CertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/certificate")
public class RestCertificateController {
    @Autowired
    CertificateService certificateService;

    @RequestMapping(value = "/getAllCertificate",method = RequestMethod.GET)
    ResponseEntity<List<CertificateDto>> getAll(){
        return new ResponseEntity<>(certificateService.getAll(), HttpStatus.OK);
    }
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    ResponseEntity<CertificateDto> addCertificate(@RequestBody CertificateDto dto){
        return new ResponseEntity<>(certificateService.add(dto),HttpStatus.OK);
    }
    @RequestMapping(value = "/update/{id}",method = RequestMethod.PUT)
    ResponseEntity<CertificateDto> updateCertificate(@PathVariable UUID id,@RequestBody CertificateDto dto){
        return new ResponseEntity<>(certificateService.update(id,dto),HttpStatus.OK);
    }
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    ResponseEntity<Boolean> delete(@PathVariable UUID id){
        return new ResponseEntity<>(certificateService.delete(id) ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }
    @RequestMapping(value = "/getById/{id}", method = RequestMethod.GET)
    ResponseEntity<CertificateDto> getById(@PathVariable UUID id){
        return new ResponseEntity<>(certificateService.getById(id),HttpStatus.OK);
    }
}
