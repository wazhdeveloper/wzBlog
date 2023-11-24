package com.wz.blogbackstage.service;

import java.io.File;

/**
 * @author wazh
 * @since 2023-10-22-11:08
 */
public interface OssUploadService {
    String uploadFile(File file);
}
