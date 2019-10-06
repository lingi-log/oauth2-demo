package com.oauth2resourceserver.api.monitoring.service;

public interface MonitoringService{
    /**
     * CPU 정보 조회
     * 
     * @param
     * @return
     * @throws Exception
     */
    public String getCPUInfo() throws Exception;
    
    /**
     * CPU 사용량 조회
     * 
     * @param
     * @return
     * @throws Exception
     */
    public String getCPUUsage() throws Exception;

    /**
     * 메모리 정보 조회
     * 
     * @param
     * @return
     * @throws Exception
     */
    public String getMemInfo() throws Exception;
    
    /**
     * 메모리 사용량 조회
     * 
     * @param
     * @return
     * @throws Exception
     */
    public String getMemUsage() throws Exception;
}