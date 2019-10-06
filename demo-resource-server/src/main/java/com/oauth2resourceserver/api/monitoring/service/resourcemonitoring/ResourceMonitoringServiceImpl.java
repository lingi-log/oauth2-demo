package com.oauth2resourceserver.api.monitoring.service.resourcemonitoring;

import java.util.Map;
import java.util.HashMap;
import java.io.BufferedReader;

import org.springframework.stereotype.Service;

import org.hyperic.sigar.CpuPerc;
import org.hyperic.sigar.FileSystem;
import org.hyperic.sigar.FileSystemUsage;
import org.hyperic.sigar.Mem;
import org.hyperic.sigar.NetInterfaceConfig;
import org.hyperic.sigar.Sigar;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import com.oauth2resourceserver.base.constants.Constants;

@Service("resourceMonitoringService")
public class ResourceMonitoringServiceImpl implements ResourceMonitoringService{
    /**
     * Disk 상태 조회
     */
    @Override
    public Map<String, Object> getDiskStatus() throws Exception{
        Map<String, Object> rtnMap = new HashMap<>();
        rtnMap.put("rtnMsg", Constants.SUCCESS);
        
		try {
            Map<String, Object> dataMap = new HashMap<>();
            
            JsonArray jArr = new JsonArray();
			JsonObject jFile = null;

			FileSystemUsage fileSystemUsage = null;
			Sigar sigar = new Sigar();
			for (FileSystem file : sigar.getFileSystemList()) {
				fileSystemUsage = sigar.getFileSystemUsage(file.getDirName());
				if (fileSystemUsage.getTotal() > 0) {

					jFile = new JsonObject();
					jFile.addProperty("filesystem", file.getDevName());
					jFile.addProperty("blocks", fileSystemUsage.getTotal() * 1024);
					jFile.addProperty("used", fileSystemUsage.getUsed() * 1024);
					jFile.addProperty("available", fileSystemUsage.getAvail() * 1024);
					jFile.addProperty("use", String.format("%.0f", fileSystemUsage.getUsePercent() * 100));
					jFile.addProperty("mounted", file.getDirName());

					jArr.add(jFile);
					jFile = null;
				}
			}

			JsonObject jObj = new JsonObject();
			jObj.addProperty("rtnMsg", "OK");
			jObj.addProperty("dfData", jArr.toString());

		} catch (Exception e) {
            rtnMap.put("rtnMsg", Constants.ERROR);
        }
        return rtnMap;
    }

    /**
     * Disk 용량 조회
     */
    @Override
    public String getTotalDisklVolume() throws Exception{
        return "";
    }
    
    /**
     * Disk 사용 용량 조회
     */
    @Override
    public String getUsedDiskVolume() throws Exception{
        return "";
    }
    
    /**
     * Disk Hierarchy 조회
     */
    @Override
    public Map<String, String> getDiskHierarchy() throws Exception{
        return new HashMap<String, String>();
    }

    /**
     * Disk 특정 폴더의 파일 목록 조회
     */
    @Override
    public String[] getDiskFiles() throws Exception{
        return new String[] {""};
    }

    /**
     * Disk 용량 정리
     */
    @Override
    public boolean setDiskClean() throws Exception{
        return true;
    }

    /**
     * 메모리 사용량 조회
     */
    @Override
    public Map<String, Object> getMemStatus() throws Exception{
        Map<String, Object> rtnMap = new HashMap<>();
        rtnMap.put("rtnMsg", Constants.SUCCESS);
        
        try{
            Map<String, Object> dataMap = new HashMap<>();
            Sigar sigar = new Sigar();
            
            // Memory
            Mem mem = sigar.getMem();
            long memTotal = mem.getTotal();
            long memUsed = mem.getActualUsed();
            
            dataMap.put("memTotal", memTotal);
            dataMap.put("memUsed", memUsed);
            
            rtnMap.put("data", dataMap);
        }catch(Exception e){
            rtnMap.put("rtnMsg", Constants.ERROR);
        }
        
        return rtnMap;
    }
    
    /**
     * CPU 정보 조회
     */
    public Map<String, Object> getCPUStatus() throws Exception{
        Map<String, Object> rtnMap = new HashMap<>();
        rtnMap.put("rtnMsg", Constants.SUCCESS);
        
        try{
            Map<String, Object> dataMap = new HashMap<>();
            Sigar sigar = new Sigar();
            
            CpuPerc cpu = sigar.getCpuPerc();
            
            dataMap.put("cpu", cpu);
            
            rtnMap.put("data", dataMap);
        }catch(Exception e){
            rtnMap.put("rtnMsg", Constants.ERROR);
        }
        

        return rtnMap;
    }
}