package com.netsmartz.mis.utils;

import lombok.Data;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class Helper {

    public static ResponseEntity<Response> getResponseEntity(Object body, String message, Integer statusCode){

        return new ResponseEntity<>(new Response(body, message,statusCode), HttpStatus.OK);
    }

}
