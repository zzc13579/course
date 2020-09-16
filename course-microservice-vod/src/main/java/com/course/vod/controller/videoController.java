package com.course.vod.controller;

import com.aliyuncs.vod.model.v20170321.CreateUploadVideoResponse;
import com.aliyuncs.vod.model.v20170321.RefreshUploadVideoResponse;
import com.course.common.vo.R;
import com.course.vod.service.VideoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @auther shanhen
 * @create 2020-09-12 12:49
 */
@Api(description = "视频管理")
@CrossOrigin
@RestController
@RequestMapping("/admin/vod/video")
public class videoController {

    @Autowired
    private VideoService videoService;

    @ApiOperation(value = "视频上传")
    @PostMapping("upload")
    public R upload(
            @ApiParam(name = "file", value = "视频文件", required = true)
            @RequestParam("file")MultipartFile file
            ){
        String videoId = videoService.uploadVideo(file);
        return R.ok().message("视频上传成功").data("videoId",videoId);
    }

    @ApiOperation(value = "视频删除")
    @DeleteMapping("{videoId}")
    public R removeVideo(
            @ApiParam(name = "videoId", value = "视频ID", required = true)
            @PathVariable String videoId
    ){
        videoService.removeVideo(videoId);
        return R.ok().message("视频删除成功");
    }

    @ApiOperation(value = "获取视频凭证")
    @GetMapping("video-play-auth/{videoId}")
    public R getVideoPlayAuth(
            @ApiParam(name = "videoId", value = "视频ID", required = true)
            @PathVariable String videoId
    ){
        String videoPlayAuth = videoService.getVideoPlayAuth(videoId);
        return R.ok().data("videoPlayAuth",videoPlayAuth);
    }

    @ApiOperation(value = "获取视频播放")
    @GetMapping("video-play-info/{videoId}")
    public R getVideoPlay(
            @ApiParam(name = "videoId", value = "视频ID", required = true)
            @PathVariable String videoId
    ){
        String videoPlayInfo = videoService.getPlayInfo(videoId);
        return R.ok().data("videoPlayInfo",videoPlayInfo);
    }

    @ApiOperation(value = "获取视频地址凭证")
    @GetMapping("get-upload-auth-and-address/{title}/{fileName}")
    public R getUploadAuthAndAddress(
            @ApiParam(name = "title", value = "视频标题", required = true)
            @PathVariable String title,
            @ApiParam(name = "fileName", value = "视频文件名", required = true)
            @PathVariable String fileName
    ){
        CreateUploadVideoResponse response = videoService.getUploadAuthAndAddress(title, fileName);
        return R.ok().message("获取视频上传地址和凭证成功").data("response",response);
    }

    @ApiOperation(value = "刷新视频地址凭证")
    @GetMapping("refresh-upload-auth-and-address/{videoId}")
    public R refreshUploadAuthAndAddress(
            @ApiParam(name = "videoId", value = "视频ID", required = true)
            @PathVariable String videoId
    ){
        RefreshUploadVideoResponse response = videoService.refreshUploadVideo(videoId);
        return R.ok().message("刷新上传地址和凭证成功").data("response",response);
    }
}
