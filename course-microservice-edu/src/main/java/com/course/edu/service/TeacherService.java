package com.course.edu.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.course.edu.entity.Teacher;
import com.baomidou.mybatisplus.extension.service.IService;
import com.course.edu.entity.TeacherQuery;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author ShanHen
 * @since 2020-09-11
 */
public interface TeacherService extends IService<Teacher> {

    void pageQuery(Page<Teacher> pageParam, TeacherQuery teacherQuery);
}
