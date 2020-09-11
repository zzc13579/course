package com.course.edu.controller.admin;

import com.course.common.vo.R;
import com.course.edu.entity.Chapter;
import com.course.edu.service.ChapterService;
import com.course.edu.vo.ChapterVo;
import com.course.edu.vo.SubjectNestedVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @auther shanhen
 * @create 2020-09-11 23:01
 */
@Api(description = "章节管理")
@CrossOrigin
@RestController
@RequestMapping("/admin/edu/chapter")
public class AdminChapterController {
    @Autowired
    private ChapterService chapterService;

    @ApiOperation(value = "新增章节")
    @PostMapping
    public R save(
            @ApiParam(name = "chapter", value = "章节对象", required = true)
            @RequestBody Chapter chapter){

        chapterService.save(chapter);
        return R.ok();
    }

    @ApiOperation(value = "根据ID修改章节")
    @PutMapping("{id}")
    public R updateById(
            @ApiParam(name = "id", value = "章节ID", required = true)
            @PathVariable String id,
            @ApiParam(name = "chapter", value = "章节对象", required = true)
            @RequestBody Chapter chapter
            ){

        chapter.setId(id);
        chapterService.updateById(chapter);
        return R.ok();
    }

    @ApiOperation(value = "根据ID查询章节")
    @GetMapping("{id}")
    public R getById(
            @ApiParam(name = "id", value = "章节ID", required = true)
            @PathVariable String id
    ){
        Chapter chapter = chapterService.getById(id);
        return R.ok().data("item",chapter);
    }

    @ApiOperation(value = "根据ID删除章节")
    @DeleteMapping("{id}")
    public R removeById(
            @ApiParam(name = "id", value = "章节ID", required = true)
            @PathVariable String id
    ){
        chapterService.removeChapterById(id);
        return R.ok();
    }

    @ApiOperation(value = "嵌套数据")
    @GetMapping("nested-list/{courseId}")
    public R nestedList(
            @ApiParam(name = "courseId", value = "课程ID", required = true)
            @PathVariable String courseId
    ){
        List<ChapterVo> chapterVos =  chapterService.nestedList(courseId);
        return R.ok().data("item",chapterVos);
    }
}
