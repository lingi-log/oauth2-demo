package com.oauth2resourceserver.base.handler;

import javax.servlet.http.HttpServletRequest;

import com.oauth2resourceserver.base.exception.BusinessLogicException;
import com.oauth2resourceserver.base.response.ResponseBodyDTO;
import com.oauth2resourceserver.base.response.code.ResponseCodeEnum;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
// @Order(Ordered.HIGHEST_PRECEDENCE)
public class CustomExceptionHandler{
    private ResponseBodyDTO<Object> responseBodyDTO;

    @Autowired
    public CustomExceptionHandler(ResponseBodyDTO<Object> responseBodyDTO){
        this.responseBodyDTO = responseBodyDTO;
    }

    @ExceptionHandler(BusinessLogicException.class)
    @ResponseBody
    public ResponseEntity<ResponseBodyDTO<Object>> handleBusinessLogicException(HttpServletRequest req, BusinessLogicException e){
        responseBodyDTO.setData(e);
        responseBodyDTO.setResponseCode(ResponseCodeEnum.F_DASHBOARD_BUSINESS_LOGIC_ERROR);

        return new ResponseEntity<ResponseBodyDTO<Object>>(responseBodyDTO, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}