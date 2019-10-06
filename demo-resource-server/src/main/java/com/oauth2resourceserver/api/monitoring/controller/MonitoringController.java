package com.oauth2resourceserver.api.monitoring.controller;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.oauth2resourceserver.api.monitoring.service.swmonitoring.SwMonitoringService;
import com.oauth2resourceserver.api.monitoring.service.resourcemonitoring.ResourceMonitoringService;
import com.oauth2resourceserver.api.monitoring.service.LogTailService;
import com.oauth2resourceserver.base.constants.Constants;
import com.oauth2resourceserver.base.response.code.ResponseCodeEnum;
import com.oauth2resourceserver.base.response.ResponseBodyDTO;

import java.util.Map;
import java.util.HashMap;

@RestController
@EnableAutoConfiguration
@RequestMapping("/api/monitoring")
public class MonitoringController {
    private LogTailService logTailService;
    private SwMonitoringService swMonitoringService;
    private ResourceMonitoringService resourceMonitoringService;
    private ResponseBodyDTO<Object> responseBodyDTO;

    @Autowired
    MonitoringController(LogTailService logTailService,
                         SwMonitoringService swMonitoringService,
                         ResourceMonitoringService resourceMonitoringService,
                         ResponseBodyDTO<Object> responseBodyDTO){
        this.logTailService = logTailService;
        this.swMonitoringService = swMonitoringService;
        this.resourceMonitoringService = resourceMonitoringService;
        this.responseBodyDTO = responseBodyDTO;
    }



    @GetMapping(value="/sw/alive-check")
    public ResponseEntity<ResponseBodyDTO<Object>> aliveCheck(@RequestParam(name="swName", required = false, defaultValue="tomcat,nginx,mariadb") String swName) throws Exception{
        try{
            responseBodyDTO.setData(swMonitoringService.aliveCheck(swName.split(",")));
        }catch(Exception e){
            responseBodyDTO.setResponseCode(ResponseCodeEnum.F_MONITORING_BUSINESS_LOGIC_ERROR);
        }
        return new ResponseEntity<ResponseBodyDTO<Object>>(responseBodyDTO, HttpStatus.OK);
    }    
    
    @GetMapping(value="/resource/state")
    public Map<String, Object> resourceStatus(@RequestParam(name="q", required = false) String resourceName) throws Exception{
        Map<String, Object> rtnMap = new HashMap<>();
        
        return rtnMap;
    }
    
    // @GetMapping(value="/log-tail")
    // public ResponseEntity<ResponseBodyDTO<Object>> logTail(@RequestParam("swName") String swName, @RequestParam(name="filePointer", defaultValue="0") Long filePointer) throws Exception{
    //     try{
    //         responseBodyDTO.setData(swMonitoringService.logTailOf(swName, filePointer));
    //     }catch(Exception e){
    //         responseBodyDTO.setResponseCode(ResponseCodeEnum.F_MONITORING_BUSINESS_LOGIC_ERROR);
    //     }
    //     return new ResponseEntity<ResponseBodyDTO<Object>>(responseBodyDTO, HttpStatus.OK);
    // }
    @GetMapping(value="/log-tail")
    public Map<String, Object> logTail(@RequestParam("swName") String swName, @RequestParam(name="filePointer", defaultValue="0") Long filePointer) throws Exception{
        Map<String, Object> rtnMap = new HashMap<>();
        rtnMap.put("rtnMsg", Constants.SUCCESS);
        try{
            Map<String, Object> dataMap = new HashMap<>();

            rtnMap.put("data", swMonitoringService.logTailOf(swName, filePointer));
        }catch(Exception e){
            rtnMap.put("rtnMsg", Constants.ERROR);
        }
        return rtnMap;
    }

    @GetMapping(value="/restart")
    public ResponseEntity<ResponseBodyDTO<Object>> restart(@RequestParam("q") String swName) throws Exception{
        try{
            if(Constants.SwState.RUN.equals(swMonitoringService.stateOf(swName))){
                responseBodyDTO.setData(swMonitoringService.stopSwName(swName));
            }else{
                responseBodyDTO.setData(swMonitoringService.startSwName(swName));
            }
        }catch(Exception e){
            responseBodyDTO.setResponseCode(ResponseCodeEnum.F_MONITORING_BUSINESS_LOGIC_ERROR);
        }
        return new ResponseEntity<ResponseBodyDTO<Object>>(responseBodyDTO, HttpStatus.OK);
    }
}