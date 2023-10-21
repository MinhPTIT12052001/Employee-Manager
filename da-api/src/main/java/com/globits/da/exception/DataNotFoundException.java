package com.globits.da.exception;

import com.globits.da.commons.ApiError;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class DataNotFoundException extends RuntimeException{
    private final ApiError apiError;
}
