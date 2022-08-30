package com.zrj.oss.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件上传至阿里云的接口
 */
@Service
public interface FileService {
    String upload(MultipartFile file);
}
