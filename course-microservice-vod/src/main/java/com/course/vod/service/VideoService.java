package com.course.vod.service;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.vod.model.v20170321.CreateUploadVideoResponse;
import com.aliyuncs.vod.model.v20170321.GetPlayInfoResponse;
import com.aliyuncs.vod.model.v20170321.RefreshUploadVideoResponse;
import org.springframework.web.multipart.MultipartFile;

/**
 * @auther shanhen
 * @create 2020-09-12 12:25
 */
public interface VideoService {
    String uploadVideo(MultipartFile file);

    void removeVideo(String videoId);

    String getVideoPlayAuth(String videoId);

    String getPlayInfo(String videoId);

    CreateUploadVideoResponse getUploadAuthAndAddress(String title, String fileName);

    RefreshUploadVideoResponse refreshUploadVideo(String videoId);
}
