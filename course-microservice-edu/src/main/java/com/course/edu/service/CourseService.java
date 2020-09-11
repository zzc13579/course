package com.course.edu.service;

import com.course.edu.entity.Course;
import com.baomidou.mybatisplus.extension.service.IService;
import com.course.edu.vo.CourseInfoForm;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author ShanHen
 * @since 2020-09-11
 */
public interface CourseService extends IService<Course> {

    String saveCourseInfo(CourseInfoForm courseInfoForm);

    CourseInfoForm getCourseInfoFormById(String id);

    void updateCourseInfoById(CourseInfoForm courseInfoForm);

    void removeCourseById(String id);
}
