package com.globits.da.rest;

import com.globits.da.AFFakeConstants;
import com.globits.da.dto.CertificateMapDto;
import com.globits.da.exception.InvalidApiDataException;
import com.globits.da.service.CertificateMapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/conferring")
public class RestCertificateMapController {

    @Autowired
    CertificateMapService certificateMapService;

    @RequestMapping(value = "/conferrings", method = RequestMethod.GET)
    public ResponseEntity<List<CertificateMapDto>> getAll() {
        List<CertificateMapDto> result = certificateMapService.getAll();
        return ResponseEntity.ok()
                .body(result);
    }

    @RequestMapping(value = "/employee/{id}", method = RequestMethod.GET)
    public ResponseEntity<List<CertificateMapDto>> getByEmployee(@PathVariable UUID id) {
        List<CertificateMapDto> result = certificateMapService.getByEmployeeId(id);
        return ResponseEntity.ok()
                .body(result);
    }

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public ResponseEntity<CertificateMapDto> addCertificateToEmployee(@Valid @RequestBody CertificateMapDto dto) throws InvalidApiDataException {
        CertificateMapDto result = certificateMapService.save(dto);
        return ResponseEntity.ok()
                .body(result);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<CertificateMapDto> updateCertificateToEmployee(@Valid @RequestBody CertificateMapDto dto,
                                                                         @PathVariable UUID id) throws InvalidApiDataException {
        CertificateMapDto result = certificateMapService.update(dto, id);
        return ResponseEntity.ok()
                .body(result);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Boolean> deleteCertificateOfEmployee(@PathVariable UUID id) {
        Boolean result = certificateMapService.deleteById(id);
        return ResponseEntity.ok()
                .body(result);
    }

}
