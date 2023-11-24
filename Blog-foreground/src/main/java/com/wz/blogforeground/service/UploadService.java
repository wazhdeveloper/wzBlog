package com.wz.blogforeground.service;

import com.wz.blogcommon.result.ResponseResult;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author wazh
 * @since 2023-10-16-8:43
 */
public interface UploadService {

    public ResponseResult<?> uploadAvatar(MultipartFile file);
}
