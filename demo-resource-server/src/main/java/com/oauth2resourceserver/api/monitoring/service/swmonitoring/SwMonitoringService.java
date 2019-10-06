package com.oauth2resourceserver.api.monitoring.service.swmonitoring;

import java.util.List;
import java.util.Map;
import org.springframework.http.ResponseEntity;
// import org.springframework.core.io.InputStreamResource;

public interface SwMonitoringService{
    /**
     * 
     * @param swNameArr
     * @return
     * @throws Exception
     */
    public List<Map<String, Object>> aliveCheck(String[] swNameArr) throws Exception;

    /**
     * 
     * @param type
     * @param filePointer
     * @return
     * @throws Exception
     */
    public Map<String, Object> logTailOf(String type, long filePointer) throws Exception;

    /**
     * param으로 전달받은 serverId, swName 의 status를 받는다.
     * @param name
     * @return
     */
    public String stateOf(String swName);

    /**
     *  
     * @param swName
     * @return
     * @throws Exception
     */
    public Map<String, Object> stopSwName(String swName) throws Exception;
    
    /**
     *  
     * @param swName
     * @return
     * @throws Exception
     */
    public Map<String, Object> startSwName(String swName) throws Exception;

    /**
     *  
     * @param swName
     * @return
     * @throws Exception
     */
    public ResponseEntity<Object> logDownload(String swName);
}