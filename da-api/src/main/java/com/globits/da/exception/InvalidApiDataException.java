package com.globits.da.exception;

import com.globits.da.commons.ApiError;

import java.util.List;

public class InvalidApiDataException extends RuntimeException{
    private final List<ApiError> apiError;

    public InvalidApiDataException(List<ApiError> apiError) {
        this.apiError = apiError;
    }
}
