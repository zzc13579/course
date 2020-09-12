package com.course.edu.service;

import com.course.edu.entity.Video;
import com.baomidou.mybatisplus.extension.service.IService;
import com.course.edu.vo.VideoInfoForm;

/**
 * <p>
 * 课程视频 服务类
 * </p>
 *
 * @author ShanHen
 * @since 2020-09-11
 */
public interface VideoService extends IService<Video> {

    void saveVideoInfo(VideoInfoForm videoInfoForm);

    VideoInfoForm getVideoInfoFormById(String id);

    void updateVideoInfoById(VideoInfoForm videoInfoForm);

    void removeVideoById(String id);
}
