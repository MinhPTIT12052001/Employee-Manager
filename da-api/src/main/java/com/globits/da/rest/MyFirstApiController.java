package com.globits.da.rest;

import com.globits.da.AFFakeConstants;
import com.globits.da.dto.MyFirstApiDto;
import com.globits.da.service.MyFirstApiService;
import com.globits.da.service.impl.MyFirstApiServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api")
public class MyFirstApiController {
    @Autowired
    private MyFirstApiService myFirstApiService;

    //@Secured({AFFakeConstants.ROLE_ADMIN, AFFakeConstants.ROLE_SUPER_ADMIN})
    @GetMapping()
    public String getMyFirstApi(){
        return "MyFirstApi";
    }

    //@Secured({AFFakeConstants.ROLE_ADMIN, AFFakeConstants.ROLE_SUPER_ADMIN})
    @GetMapping("/service")
    public String getMyFirstApiService(){
        return myFirstApiService.getMyFirstApiService();
    }
    //@Secured({AFFakeConstants.ROLE_ADMIN, AFFakeConstants.ROLE_SUPER_ADMIN})
    @PostMapping("/postMyApi")
    public MyFirstApiDto postMyFirstApiDto(@RequestBody MyFirstApiDto myFirstApiDto){
        return myFirstApiDto;
    }
    @PostMapping("/param")
    public MyFirstApiDto postMyFirstApiParam(@RequestParam String code,
                                             @RequestParam String name,
                                             @RequestParam Integer age){
        return new MyFirstApiDto(code,name,age);
    }
    @PostMapping("/variable/{code}/{name}/{age}")
    public MyFirstApiDto postMyFirstApiVariable(@PathVariable String code,
                                                @PathVariable String name,
                                                @PathVariable Integer age){
        return new MyFirstApiDto(code,name,age);
    }
    @PostMapping("/formdata")
    public MyFirstApiDto postMyFirstApiFormdata(HttpServletRequest request){
        return myFirstApiService.postApiFormdata(request);
    }
    @PostMapping("/json")
    public MyFirstApiDto postMyFirstApiJson(HttpServletRequest request){
        return myFirstApiService.postApiJson(request);
    }
    @PostMapping(value = "file")
    public void postMyFirstApiFile(@RequestParam("file")MultipartFile file){
        myFirstApiService.postApiFile(file);
    }
}
