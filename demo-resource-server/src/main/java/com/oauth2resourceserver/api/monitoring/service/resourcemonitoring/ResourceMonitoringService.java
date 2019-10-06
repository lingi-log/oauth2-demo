package com.oauth2resourceserver.api.monitoring.service.resourcemonitoring;

import java.util.Map;

public interface ResourceMonitoringService{
    /**
     * Disk 상태 조회
     * 
     * @param
     * @return
     * @throws Exception
     */
    public Map<String, Object> getDiskStatus() throws Exception;
    
    /**
     * Disk 용량 조회
     * 
     * @param
     * @return
     * @throws Exception
     */
    public String getTotalDisklVolume() throws Exception;
    
    /**
     * Disk 용량 조회
     * 
     * @param
     * @return
     * @throws Exception
     */
    public String getUsedDiskVolume() throws Exception;
    
    /**
     * Disk Hierarchy 조회
     * 
     * @param
     * @return
     * @throws Exception
     */
    public Map<String, String> getDiskHierarchy() throws Exception;

    /**
     * Disk 특정 폴더의 파일 목록 조회
     * 
     * @param
     * @return
     * @throws Exception
     */
    public String[] getDiskFiles() throws Exception;

    /**
     * Disk 용량 정리
     * 
     * @param
     * @return
     * @throws Exception
     */
    public boolean setDiskClean() throws Exception;

    /**
     * 메모리 사용량 조회
     * 
     * @param
     * @return
     * @throws Exception
     */
    public Map<String, Object> getMemStatus() throws Exception;

    /**
     * CPU 정보 조회
     * 
     * @param
     * @return
     * @throws Exception
     */
    public Map<String, Object> getCPUStatus() throws Exception;
}