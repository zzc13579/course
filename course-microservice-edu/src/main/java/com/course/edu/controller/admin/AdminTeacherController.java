package com.course.edu.controller.admin;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.course.common.vo.R;
import com.course.edu.entity.Teacher;
import com.course.edu.entity.TeacherQuery;
import com.course.edu.service.TeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @auther shanhen
 * @create 2020-09-11 12:04
 */
@Api(description = "讲师")
@RestController
@CrossOrigin
@RequestMapping(value = "/admin/edu/teacher")
public class AdminTeacherController {

    @Autowired
    TeacherService teacherService;

    @ApiOperation(value = "新增讲师")
    @PostMapping
    public R save(
            @ApiParam(name = "teacher", value = "讲师对象", required = true)
            @RequestBody Teacher teacher
    ){
        teacherService.save(teacher);
        return R.ok();
    }

    @ApiOperation(value = "根据Id查询讲师")
    @GetMapping("{teacherId}")
    public R getById(
            @ApiParam(name = "id", value = "讲师ID", required = true)
            @PathVariable String teacherId
    ){
        Teacher teacher = teacherService.getById(teacherId);
        return R.ok().data("teacher",teacher);
    }

    @ApiOperation(value = "根据Id修改讲师")
    @PutMapping("{teacherId}")
    public R updateById(
            @ApiParam(name = "teacherId", value = "讲师ID", required = true)
            @PathVariable String teacherId,
            @ApiParam(name = "teacher", value = "讲师对象", required = true)
            @RequestBody Teacher teacher
    ){
        teacher.setId(teacherId);
        teacherService.updateById(teacher);
        return R.ok();
    }

    @ApiOperation(value = "所有讲师列表")
    @GetMapping
    public R list(){
        List<Teacher> list = teacherService.list(null);
        return R.ok().data("list",list);
    }

    @ApiOperation(value = "分页列表查询")
    @GetMapping("{page}/{limit}")
    public R pageQuery(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Long page,
            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit,
            @ApiParam(name = "teacher", value = "讲师对象", required = false)
            TeacherQuery teacherQuery
    ){
        Page<Teacher> pageParam = new Page<>(page, limit);

        teacherService.pageQuery(pageParam,teacherQuery);
        List<Teacher> records = pageParam.getRecords();
        long total = pageParam.getTotal();

        return R.ok().data("total",total).data("records",records);
    }

    @ApiOperation(value = "删除讲师")
    @DeleteMapping("{teacherId}")
    public R save(
            @ApiParam(name = "teacherId", value = "讲师ID", required = true)
            @PathVariable String teacherId
    ){
        teacherService.removeById(teacherId);
        return R.ok();
    }
}
