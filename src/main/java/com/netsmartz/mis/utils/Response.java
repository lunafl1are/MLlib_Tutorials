package com.netsmartz.mis.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Response {

    private Object body;

    private String message;

    private Integer statusCode;
}
