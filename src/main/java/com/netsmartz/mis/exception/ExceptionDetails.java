package com.netsmartz.mis.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionDetails {

    private int status;
    private Date timeStamp;
    private String message;

    private String debugMessage;

}
