package com.library.librarymanagementsystem.response.SuccessResponse;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class ApiResponse {
    public ResponseEntity<?> apiResponse(boolean success,
                                                     HttpStatus httpStatus, Object respObject, String message) {
        Map<String, Object> resMap = new HashMap<>();
        resMap.put("success", success);
        resMap.put("response code", httpStatus.value());
        if(success)resMap.put("data", respObject);
        if(success)resMap.put("success msg", message);
        else resMap.put("error msg", message);
        return new ResponseEntity<>(resMap, httpStatus);
    }


    public ResponseEntity<?> apiResponse(boolean success,
                                                     HttpStatus httpStatus, String message) {
        Map<String, Object> resMap = new HashMap<>();
        resMap.put("success", success);
        resMap.put("response code", httpStatus.value());
        if(success)resMap.put("success msg", message);
        else resMap.put("error msg", message);
        return new ResponseEntity<>(resMap, httpStatus);
    }
}