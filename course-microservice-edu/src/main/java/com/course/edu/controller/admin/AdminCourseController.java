package com.course.edu.controller.admin;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.course.common.vo.R;
import com.course.edu.entity.Course;
import com.course.edu.service.CourseService;
import com.course.edu.vo.CourseInfoForm;
import com.course.edu.vo.CoursePublishVo;
import com.course.edu.vo.CourseQuery;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @auther shanhen
 * @create 2020-09-11 20:40
 */
@Api(description = "课程管理")
@CrossOrigin
@RestController
@RequestMapping(value = "/admin/edu/course")
public class AdminCourseController {

    @Autowired
    private CourseService courseService;

    @ApiOperation(value = "新增课程")
    @PostMapping("save-course-info")
    public R saveCourseInfo(
            @ApiParam(name = "courseInfoForm", value = "课程表单对象", required = true)
            @RequestBody CourseInfoForm courseInfoForm
            ){
               String courseId =  courseService.saveCourseInfo(courseInfoForm);
               return R.ok().data("courseId",courseId);
    }

    @ApiOperation(value = "根据ID删除课程")
    @DeleteMapping("{id}")
    public R removeCourseById(
            @ApiParam(name = "id", value = "课程ID",required = true)
            @PathVariable String id
    ){
        courseService.removeCourseById(id);
        return R.ok();
    }

    @ApiOperation(value = "根据ID查询课程")
    @GetMapping("{id}")
    public R getById(
            @ApiParam(name = "id", value = "课程ID",required = true)
            @PathVariable String id
    ){
        CourseInfoForm courseInfoForm = courseService.getCourseInfoFormById(id);
        return R.ok().data("item",courseInfoForm);
    }

    @ApiOperation(value = "更新课程")
    @PutMapping("update-course-info/{id}")
    public R updateCourseInfoById(
            @ApiParam(name = "courseInfoForm", value = "课程对象",required = true)
            @RequestBody CourseInfoForm courseInfoForm
    ){
        courseService.updateCourseInfoById(courseInfoForm);
        return R.ok();
    }

    @ApiOperation(value = "根据获取ID课程发布信息")
    @GetMapping("course-publish-info/{id}")
    public R getCoursePublishVoById(
            @ApiParam(name = "id", value = "课程ID",required = true)
            @PathVariable String id
    ){
        CoursePublishVo coursePublishVoById = courseService.getCoursePublishVoById(id);
        return R.ok().data("item",coursePublishVoById);
    }

    @ApiOperation(value = "根据id发布课程")
    @PutMapping("publish-course/{id}")
    public R publishCourseById(
            @ApiParam(name = "id", value = "课程ID", required = true)
            @PathVariable String id){

        courseService.publishCourseById(id);
        return R.ok();
    }

    @ApiOperation(value = "分页课程列表")
    @GetMapping("{page}/{limit}")
    public R pageQuery(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Long page,

            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit,

            @ApiParam(name = "courseQuery", value = "查询对象", required = false)
                    CourseQuery courseQuery){

        Page<Course> pageParam = new Page<>(page, limit);

        courseService.pageQuery(pageParam, courseQuery);
        List<Course> records = pageParam.getRecords();

        long total = pageParam.getTotal();

        return  R.ok().data("total", total).data("rows", records);
    }
}
