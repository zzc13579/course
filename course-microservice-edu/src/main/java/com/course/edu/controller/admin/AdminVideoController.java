package com.course.edu.controller.admin;

import com.course.common.vo.R;
import com.course.edu.service.VideoService;
import com.course.edu.vo.VideoInfoForm;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @auther shanhen
 * @create 2020-09-12 9:17
 */
@Api(description = "课程视频管理")
@CrossOrigin
@RestController
@RequestMapping(value = "/admin/edu/video")
public class AdminVideoController {

    @Autowired
    private VideoService videoService;

    @ApiOperation(value = "新增课程视频")
    @PostMapping("save-video-info")
    public R save(
            @ApiParam(name = "videoInfoForm", value = "视频对象", required = true)
            @RequestBody VideoInfoForm videoInfoForm
            ){
        videoService.saveVideoInfo(videoInfoForm);
        return R.ok();
    }

    @ApiOperation(value = "根据ID查询视频")
    @GetMapping("video-info/{id}")
    public R getById(
            @ApiParam(name = "id", value = "视频ID", required = true)
            @PathVariable String id
    ){
        VideoInfoForm videoInfoForm = videoService.getVideoInfoFormById(id);
        return R.ok().data("item",videoInfoForm);
    }

    @ApiOperation(value = "根据ID修改视频")
    @PutMapping("update-video-info/{id}")
    public R updateByVideo(
            @ApiParam(name = "videoInfoForm", value = "视频对象", required = true)
            @RequestBody VideoInfoForm videoInfoForm
    ){
        videoService.updateVideoInfoById(videoInfoForm);
        return R.ok();
    }

    @ApiOperation(value = "根据ID删除视频")
    @DeleteMapping("delete-video-info/{id}")
    public R removeVideoById(
            @ApiParam(name = "id", value = "视频ID", required = true)
            @PathVariable String id
    ){
        videoService.removeVideoById(id);
        return R.ok();
    }

}
