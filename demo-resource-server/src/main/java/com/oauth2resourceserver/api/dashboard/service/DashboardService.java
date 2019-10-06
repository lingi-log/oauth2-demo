package com.oauth2resourceserver.api.dashboard.service;

import org.springframework.http.ResponseEntity;

public interface DashboardService{
    /**
     * Report
     * 
     * @param
     * @return
     * @throws Exception
     */
    public Boolean report() throws Exception;

    /**
     * ServiceName의 최신 버전 가져오기
     * 
     * @param serviceName
     * @return 
     * @throws Exception
     */
    public String getLatestVersion(String serviceName) throws Exception;
    
    /**
     * HQ 서버에서 ServiceName의 version 다운로드 가져오기
     * 
     * @param serviceName
     * @param version
     * @return 
     * @throws Exception
     */
    public String downloadSwFromHqServer(String serviceName, String version) throws Exception;

    /**
     * ServiceName의 version으로 업데이트
     * 
     * @param serviceName
     * @param version
     * @return
     * @throws Exception
     */
    public Boolean update(String serviceName, String version) throws Exception;
    
    
    /**
     * sw 다운로드
     * 
     * @param swName
     * @param version
     * @return
     * @throws Exception
     */
    // public ResponseEntity<Object> swDownload(String swName, String version);
    
}