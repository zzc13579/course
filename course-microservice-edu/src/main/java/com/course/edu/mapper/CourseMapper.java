package com.course.edu.mapper;

import com.course.edu.entity.Course;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.course.edu.vo.CoursePublishVo;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author ShanHen
 * @since 2020-09-11
 */
public interface CourseMapper extends BaseMapper<Course> {
    CoursePublishVo selectCoursePublishVoById(String id);
}
