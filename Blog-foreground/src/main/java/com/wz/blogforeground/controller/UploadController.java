package com.wz.blogforeground.controller;

import com.wz.blogcommon.annotation.SystemLog;
import com.wz.blogcommon.result.AppHttpCodeEnum;
import com.wz.blogcommon.result.ResponseResult;
import com.wz.blogforeground.service.OssUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @author wazh
 * @since 2023-10-16-8:38
 */
@RestController
public class UploadController {

    @Autowired
    private OssUploadService ossUploadService;

    @PostMapping("/upload")
    @SystemLog(businessName = "上传头像到aliyun-oss对象存储服务")
    public ResponseResult<?> uploadAvatar(MultipartFile img) {
        String filename = img.getOriginalFilename();
        assert filename != null;
        String prefix = filename.substring(0, filename.indexOf("."));
        String suffix = filename.substring(filename.indexOf("."));
        try {
            File tempFile = File.createTempFile(prefix, suffix);
            img.transferTo(tempFile);
            String retPath = ossUploadService.uploadFile(tempFile);
            tempFile.deleteOnExit();
            return ResponseResult.okResult(retPath);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return ResponseResult.errorResult(AppHttpCodeEnum.SYSTEM_ERROR);
    }
}
