package com.course.oss.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @auther shanhen
 * @create 2020-09-11 14:58
 */
public interface FileService {
    String upload(MultipartFile file);
}
