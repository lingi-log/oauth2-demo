package com.oauth2resourceserver.api.dashboard.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.http.ResponseEntity;
import org.springframework.core.env.Environment;

// import com.example.demo.util.FileDownloadUtil;

@Service("dashboardService")
public class DashboardServiceImpl implements DashboardService{
    @Autowired
    Environment env;
    
    /**
     * Report
     */
    public Boolean report() throws Exception{
        return false;
    }

    /**
     * ServiceName의 최신 버전 가져오기
     */
    public String getLatestVersion(String serviceName) throws Exception{
        return "";
    }
    
    /**
     * HQ 서버에서 ServiceName의 version 다운로드 가져오기
     */
    public String downloadSwFromHqServer(String serviceName, String version) throws Exception{
        return "";
    }

    /**
     * ServiceName의 version으로 업데이트
     */
    public Boolean update(String serviceName, String version) throws Exception{
        return false;
    }
    
    /**
     * sw 다운로드
     */
    // public ResponseEntity<Object> swDownload(String swName, String version){
    //     String filePath = "";

    //     if("webmanager".equals(swName)){
    //         filePath = env.getProperty("Linux.tomcat.access.log");
    //     }

    //     return new FileDownloadUtil().getDownloadFile(filePath);
    // }
}