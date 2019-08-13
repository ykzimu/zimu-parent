package com.zimu.controller;

import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;

@Controller
@RequestMapping("/download")
public class DownLoadController {

    @GetMapping("/id")
    public ResponseEntity<byte[]> export() throws IOException {
        File file = new File("D:\\客户端对应平台和编码1.6.xlsx");
        String filename = "客户端对应平台和编码1.6.xlsx";
        filename = URLEncoder.encode(filename, "UTF-8");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", filename);
        return ResponseEntity.status(HttpStatus.CREATED).headers(headers).body(FileUtils.readFileToByteArray(file));
    }

}
