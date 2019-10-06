package com.oauth2resourceserver.base.util;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.apache.tika.Tika;

import java.io.File;
import java.io.FileInputStream;
// import java.io.IOException;

public class FileDownloadUtil{

    public ResponseEntity<Object> getDownloadFile(String filePath){
        ResponseEntity<Object> rtn;

        try{
            File file = new File(filePath);
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Disposition", "attachment; filename=\"" + file.getName() + "\";");
            headers.add("Content-Transfer-Encoding", "binary");
            headers.add("Content-Type", new Tika().detect(file) + ";charset=UTF-8");
            
            rtn = ResponseEntity.ok().headers(headers).contentLength(file.length())
            .contentType(MediaType.parseMediaType("application/octet-stream"))
            .body(new InputStreamResource(new FileInputStream(file)));

        }catch(Exception e){
            rtn = ResponseEntity.badRequest().build();
        }

        return rtn;
    }
}