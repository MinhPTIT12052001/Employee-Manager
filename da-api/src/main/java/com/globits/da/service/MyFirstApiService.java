package com.globits.da.service;

import com.globits.da.dto.MyFirstApiDto;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

public interface MyFirstApiService {
    String getMyFirstApiService();
    MyFirstApiDto postApiFormdata(HttpServletRequest request);
    MyFirstApiDto postApiJson(HttpServletRequest request);
    void postApiFile(MultipartFile file);
}
