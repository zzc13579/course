package com.course.edu.controller.admin;

import com.course.common.vo.R;
import com.course.edu.service.SubjectService;
import com.course.edu.vo.SubjectNestedVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @auther shanhen
 * @create 2020-09-11 16:19
 */
@Api(description = "课程分类管理")
@RestController
@CrossOrigin
@RequestMapping(value = "/admin/edu/subject")
public class AdminSubjectController {

    @Autowired
    SubjectService subjectService;

    @ApiOperation(value = "导入excel")
    @PostMapping("import")
    public R batchImport(
            @ApiParam(name = "file",value = "导入excel文件", required = true)
            @RequestParam("file") MultipartFile file
            ){
            subjectService.batchImport(file,subjectService);
            return R.ok().message("文件上传成功");
    }

    @ApiOperation(value = "嵌套数据")
    @GetMapping()
    public R nestedList(){
        List<SubjectNestedVo> subjectNestedList =  subjectService.nestedList();
        return R.ok().data("item",subjectNestedList);
    }
}
