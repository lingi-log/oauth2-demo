package com.oauth2resourceserver.api.dashboard.controller;

import javax.annotation.Resource;
import java.util.Map;
import java.util.HashMap;

import com.oauth2resourceserver.api.dashboard.service.DashboardService;
import com.oauth2resourceserver.base.exception.BusinessLogicException;
import com.oauth2resourceserver.base.response.ResponseBodyDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import com.oauth2resourceserver.base.response.code.ResponseCodeEnum;


@RestController
@EnableAutoConfiguration
@RequestMapping("/api/dashboard")
public class DashboardController {
    @Resource(name="dashboardService")
    private DashboardService service;
    private ResponseBodyDTO<Object> responseBodyDTO;

    @Autowired
    public DashboardController(ResponseBodyDTO<Object> responseBodyDTO){
        this.responseBodyDTO = responseBodyDTO;
    }

    @GetMapping(value="/summary")
    public ResponseEntity<ResponseBodyDTO<Object>> summary() throws Exception{
        try{
            Map<String, Object> dataMap = new HashMap<>();

            dataMap.put("model", "MacBook Pro 2018");
            dataMap.put("cpu", "cpu model");
            dataMap.put("mem", "16gb");
            dataMap.put("disk", "512gb");
            dataMap.put("ksver", "ksver");
            dataMap.put("tomcat", "tomcat");
            dataMap.put("nginx", "nginx");
            dataMap.put("db", "mariadb");

            responseBodyDTO.setData(dataMap);
        }catch(Exception e){
            throw new BusinessLogicException();
        }

        return new ResponseEntity<ResponseBodyDTO<Object>>(responseBodyDTO, HttpStatus.OK);
    }

    @GetMapping(value="/summary2")
    public void summary2() throws Exception{
        throw new BusinessLogicException();
    }
}