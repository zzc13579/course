package com.course.edu.service;

import com.course.edu.entity.Chapter;
import com.baomidou.mybatisplus.extension.service.IService;
import com.course.edu.vo.ChapterVo;
import com.course.edu.vo.SubjectNestedVo;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author ShanHen
 * @since 2020-09-11
 */
public interface ChapterService extends IService<Chapter> {

    void removeChapterById(String id);

    List<ChapterVo> nestedList(String courseId);
}
