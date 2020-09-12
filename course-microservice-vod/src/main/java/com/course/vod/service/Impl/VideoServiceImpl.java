package com.course.vod.service.Impl;

import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadStreamRequest;
import com.aliyun.vod.upload.resp.UploadStreamResponse;
import com.aliyun.vod.upload.resp.UploadVideoResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.vod.model.v20170321.*;
import com.course.common.constants.ResultCodeEnum;
import com.course.common.exception.CourseException;
import com.course.vod.service.VideoService;
import com.course.vod.util.AliyunVodSDKUtils;
import com.course.vod.util.ConstantPropertiesUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @auther shanhen
 * @create 2020-09-12 12:28
 */
@Service
public class VideoServiceImpl implements VideoService {
    @Override
    public String uploadVideo(MultipartFile file) {

        String fileName = file.getOriginalFilename();
        String title = fileName.substring(0, fileName.lastIndexOf("."));
        InputStream fileInputStream = null;
        try {
            fileInputStream = file.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }

        UploadStreamRequest request = new UploadStreamRequest(ConstantPropertiesUtil.ACCESS_KEY_ID, ConstantPropertiesUtil.ACCESS_KEY_SECRET, title, fileName, fileInputStream);
        UploadVideoImpl uploader = new UploadVideoImpl();
        UploadStreamResponse response = uploader.uploadStream(request);
        String videoId = response.getVideoId();
        if(!response.isSuccess()){
            if(StringUtils.isEmpty(videoId)){
                throw new CourseException(ResultCodeEnum.VIDEO_UPLOAD_ALIYUN_ERROR);
            }
        }
        return videoId;
    }

    @Override
    public void removeVideo(String videoId) {
        DefaultAcsClient client = null;
        try {
            client = AliyunVodSDKUtils.initVodClient(ConstantPropertiesUtil.ACCESS_KEY_ID, ConstantPropertiesUtil.ACCESS_KEY_SECRET);
            DeleteVideoRequest request = new DeleteVideoRequest();
            request.setVideoIds(videoId);
            DeleteVideoResponse response = client.getAcsResponse(request);
        } catch (ClientException e) {
            throw new CourseException(ResultCodeEnum.VIDEO_DELETE_ERROR);
        }
    }

    @Override
    public String getVideoPlayAuth(String videoId) {
        DefaultAcsClient client = null;
        GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();
        GetVideoPlayAuthResponse response = new GetVideoPlayAuthResponse();
        try {
            client = AliyunVodSDKUtils.initVodClient(ConstantPropertiesUtil.ACCESS_KEY_ID, ConstantPropertiesUtil.ACCESS_KEY_SECRET);

            request.setVideoId(videoId);
            response = client.getAcsResponse(request);
            System.out.print("PlayAuth = " + response.getPlayAuth() + "\n");
            //VideoMeta信息
            System.out.print("VideoMeta.Title = " + response.getVideoMeta().getTitle() + "\n");

        } catch (ClientException e) {
            throw new CourseException(ResultCodeEnum.VIDEO_DELETE_ERROR);
        }
        return response.getPlayAuth();
    }

    @Override
    public String getPlayInfo(String videoId) {
        DefaultAcsClient client = null;
        GetPlayInfoRequest request = new GetPlayInfoRequest();
        GetPlayInfoResponse response = new GetPlayInfoResponse();
        String playUrl = null;
        try {
            client = AliyunVodSDKUtils.initVodClient(ConstantPropertiesUtil.ACCESS_KEY_ID, ConstantPropertiesUtil.ACCESS_KEY_SECRET);
            request.setVideoId(videoId);
            response = client.getAcsResponse(request);
            List<GetPlayInfoResponse.PlayInfo> playInfoList = response.getPlayInfoList();
            //播放地址
            for (GetPlayInfoResponse.PlayInfo playInfo : playInfoList) {
                System.out.print("PlayInfo.PlayURL = " + playInfo.getPlayURL() + "\n");
                playUrl = playInfo.getPlayURL();
            }
        } catch (ClientException e) {
            throw new CourseException(ResultCodeEnum.VIDEO_DELETE_ERROR);
        }
        return playUrl;
    }

    @Override
    public CreateUploadVideoResponse getUploadAuthAndAddress(String title, String fileName) {

        DefaultAcsClient client = null;
        try {
            client = AliyunVodSDKUtils.initVodClient(ConstantPropertiesUtil.ACCESS_KEY_ID, ConstantPropertiesUtil.ACCESS_KEY_SECRET);
            CreateUploadVideoRequest request = new CreateUploadVideoRequest();
            request.setFileName(fileName);
            request.setTitle(title);
            CreateUploadVideoResponse response = client.getAcsResponse(request);

            return response;

        } catch (ClientException e) {
            throw new CourseException(ResultCodeEnum.VIDEO_UPLOAD_ALIYUN_ERROR);
        }
    }

    @Override
    public RefreshUploadVideoResponse refreshUploadVideo(String videoId) {
        DefaultAcsClient client = null;
        try {
            client = AliyunVodSDKUtils.initVodClient(ConstantPropertiesUtil.ACCESS_KEY_ID, ConstantPropertiesUtil.ACCESS_KEY_SECRET);
            RefreshUploadVideoRequest request = new RefreshUploadVideoRequest();
            request.setVideoId(videoId);
            RefreshUploadVideoResponse response = client.getAcsResponse(request);

            return response;

        } catch (ClientException e) {
            throw new CourseException(ResultCodeEnum.VIDEO_UPLOAD_ALIYUN_ERROR);
        }
    }
}
