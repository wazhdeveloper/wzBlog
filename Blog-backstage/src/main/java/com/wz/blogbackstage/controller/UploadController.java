package com.wz.blogbackstage.controller;

import com.wz.blogbackstage.service.OssUploadService;
import com.wz.blogcommon.annotation.SystemLog;
import com.wz.blogcommon.result.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @author wazh
 * @since 2023-10-22-11:05
 */
@Slf4j
@RestController
@RequestMapping("/upload")
public class UploadController {

    @Autowired
    private OssUploadService ossUploadService;

    @PostMapping
    @SystemLog(businessName = "上传文件")
    public ResponseResult<?> upload(@RequestBody MultipartFile img) {
        String uri = null;
        String filename = img.getOriginalFilename();
        assert filename != null;
        String prefix = filename.substring(0, filename.indexOf('.'));
        String suffix = filename.substring(filename.indexOf('.'));
        try {
            File tempFile = File.createTempFile(prefix, suffix);
            img.transferTo(tempFile);
            uri = ossUploadService.uploadFile(tempFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
            log.info("{}，返回链接：{}", "上传图片成功", uri);
        return ResponseResult.okResult(uri);
    }
}
