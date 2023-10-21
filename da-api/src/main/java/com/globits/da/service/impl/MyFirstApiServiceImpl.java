package com.globits.da.service.impl;

import com.globits.da.dto.MyFirstApiDto;
import com.globits.da.service.MyFirstApiService;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

@Service
public class MyFirstApiServiceImpl implements MyFirstApiService {

    @Override
    public String getMyFirstApiService() {
        return "MyFirstApiService";
    }
    @Override
    public MyFirstApiDto postApiFormdata(HttpServletRequest request){

        String code = request.getParameter("code");
        String name = request.getParameter("name");
        Integer age = Integer.valueOf(request.getParameter("age"));

        return new MyFirstApiDto(code,name,age);
    }
    @Override
    public MyFirstApiDto postApiJson(HttpServletRequest request){
        try{
            //read data Json from request
            BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
            StringBuilder jsonBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null){
                jsonBuilder.append(line);
            }
            reader.close();
            // Convert data Json to String
            String jsonString = jsonBuilder.toString();

            JSONObject jsonObject = new JSONObject(jsonString);
            String code = jsonObject.getString("code");
            String name = jsonObject.getString("name");
            Integer age = jsonObject.getInt("age");

            return new MyFirstApiDto(code,name,age);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void postApiFile(MultipartFile file) {
            if(!file.isEmpty()){
                try(InputStream inputStream = file.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))){
                    String line;
                    while ((line = reader.readLine()) != null){
                        System.out.println(line);
                    }
                }
                catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    }

