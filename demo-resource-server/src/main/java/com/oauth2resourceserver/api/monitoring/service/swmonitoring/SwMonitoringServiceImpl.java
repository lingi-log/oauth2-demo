package com.oauth2resourceserver.api.monitoring.service.swmonitoring;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
// import org.springframework.core.io.InputStreamResource;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Map;
import java.util.Random;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.io.File;

import com.oauth2resourceserver.base.constants.Constants;
import com.oauth2resourceserver.base.util.LogTailUtil;
import com.oauth2resourceserver.base.util.FileDownloadUtil;

@Service("swMonitoringService")
public class SwMonitoringServiceImpl implements SwMonitoringService{
    @Autowired
	Environment env;

    public List<Map<String, Object>> aliveCheck(String[] swNameArr) throws Exception{
        List<Map<String, Object>> rtnList = new ArrayList<>();
        Map<String, Object> dataMap;

        for(String swName : swNameArr){
            dataMap = new HashMap<>();
            dataMap.put("name", swName);
            dataMap.put("state", this.stateOf(swName));
            rtnList.add(dataMap);
        }

        return rtnList;
    }

    public String stateOf(String swName){
        String rtnVal = Constants.SwState.RUN;
        BufferedReader br = null;
        
        try {
            Process process = null;
            process = Runtime.getRuntime().exec(
                new String[] { "sh", "-c", "ps -ef | grep " + swName + " | grep -v grep | awk '{print $2}'" });

            br = new BufferedReader(new InputStreamReader(process.getInputStream()));
            // Random generator = new Random();
            // if(generator.nextInt(2) % 2 == 0)rtnVal = Constants.SwState.STOP;
            // else rtnVal = Constants.SwState.RUN;
            if(br.readLine() == null) rtnVal = Constants.SwState.STOP;
        } catch (IOException e) {
            rtnVal = Constants.SwState.ERROR;
        } finally {
            if (br != null) try { br.close(); } catch (Exception e) { }
        }
        return rtnVal;
    }

    public Map<String, Object> logTailOf(String type, long filePointer) throws Exception{        
        File logFile;
        
        if(System.getProperty("os.name").indexOf("Windows") > -1){
            if("tomcat".equals(type)){
                logFile = new File(env.getProperty("Windows.tomcat.access.log"));
            }else if("nginx".equals(type)){
                logFile = new File(env.getProperty("Windows.nginx.access.log"));
            }else if("db".equals(type)){
                logFile = new File(env.getProperty("Windows.mysql.log"));
            }else{
                throw new Exception();
            }
        }else{
            if("tomcat".equals(type)){
                logFile = new File(env.getProperty("Linux.tomcat.access.log"));
            }else if("nginx".equals(type)){
                logFile = new File(env.getProperty("Linux.nginx.access.log"));
            }else if("db".equals(type)){
                logFile = new File(env.getProperty("Linux.mysql.log"));
            }else{
                throw new Exception();
            }
        }

        return new LogTailUtil().runTail(logFile, filePointer);
    }

    public Map<String, Object> stopSwName(String swName) throws Exception{
        Map<String, Object> rtnMap = new HashMap<>();
        rtnMap.put("status", Constants.SUCCESS);

        return rtnMap;
    }

    public Map<String, Object> startSwName(String swName) throws Exception{
        Map<String, Object> rtnMap = new HashMap<>();
        rtnMap.put("status", Constants.SUCCESS);
        return rtnMap;
    }

    /**
     * logDownload
     * 
     * @param swName
     * @return
     * @throws Exception
     */
    public ResponseEntity<Object> logDownload(String swName){
        String filePath = "";
        if("tomcat".equals(swName)){
            filePath = env.getProperty("Linux.tomcat.access.log");
        }else if("nginx".equals(swName)){
            filePath = env.getProperty("Linux.nginx.access.log");
        }else if("db".equals(swName)){
            filePath = env.getProperty("Linux.mysql.log");
        }
        return new FileDownloadUtil().getDownloadFile(filePath);
    }
}