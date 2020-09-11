package com.course.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.course.common.exception.CourseException;
import com.course.edu.entity.Chapter;
import com.course.edu.entity.Course;
import com.course.edu.entity.CourseDescription;
import com.course.edu.entity.Video;
import com.course.edu.mapper.ChapterMapper;
import com.course.edu.mapper.CourseDescriptionMapper;
import com.course.edu.mapper.CourseMapper;
import com.course.edu.mapper.VideoMapper;
import com.course.edu.service.CourseDescriptionService;
import com.course.edu.service.CourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.course.edu.vo.CourseInfoForm;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author ShanHen
 * @since 2020-09-11
 */
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements CourseService {

    @Autowired
    private CourseDescriptionMapper courseDescriptionMapper;

    @Autowired
    private VideoMapper videoMapper;

    @Autowired
    private ChapterMapper chapterMapper;

    @Transactional
    @Override
    public String saveCourseInfo(CourseInfoForm courseInfoForm) {

        Course course = new Course();
        course.setStatus(Course.COURSE_DRAFT);
        BeanUtils.copyProperties(courseInfoForm,course);
        baseMapper.insert(course);

        CourseDescription courseDescription = new CourseDescription();
        courseDescription.setDescription(courseInfoForm.getDescription());
        courseDescription.setId(course.getId());
        courseDescriptionMapper.insert(courseDescription);

        return course.getId();
    }

    @Override
    public CourseInfoForm getCourseInfoFormById(String id) {

        Course course = baseMapper.selectById(id);
        if(course == null){
            throw new CourseException(20001,"数据不存在");
        }

        CourseDescription courseDescription = courseDescriptionMapper.selectById(id);
        if(courseDescription == null){
            throw new CourseException(20001,"数据不存在");
        }

        CourseInfoForm courseInfoForm = new CourseInfoForm();
        BeanUtils.copyProperties(course,courseInfoForm);
        BeanUtils.copyProperties(courseDescription,courseInfoForm);

        return courseInfoForm;
    }

    @Override
    public void updateCourseInfoById(CourseInfoForm courseInfoForm) {
        Course course = new Course();
        BeanUtils.copyProperties(courseInfoForm,course);
        baseMapper.updateById(course);

        CourseDescription courseDescription = new CourseDescription();
        courseDescription.setDescription(courseInfoForm.getDescription());
        courseDescription.setId(courseInfoForm.getId());
        courseDescriptionMapper.updateById(courseDescription);
    }

    @Override
    public void removeCourseById(String id) {
        QueryWrapper<Video> videoQueryWrapper = new QueryWrapper<>();
        videoQueryWrapper.eq("course_id",id);
        videoMapper.deleteById(videoQueryWrapper);

        QueryWrapper<Chapter> chapterQueryWrapper = new QueryWrapper<>();
        chapterQueryWrapper.eq("course_id",id);
        chapterMapper.deleteById(chapterQueryWrapper);

        baseMapper.deleteById(id);
    }
}
