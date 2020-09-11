package com.course.edu.util;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.read.listener.ReadListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.course.common.exception.CourseException;
import com.course.edu.entity.Subject;
import com.course.edu.entity.SubjectData;
import com.course.edu.service.SubjectService;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;

/**
 * @auther shanhen
 * @create 2020-09-11 16:33
 */
public class SubjectExcelListener extends AnalysisEventListener<SubjectData> {

    public SubjectService subjectService;
    public SubjectExcelListener(){}

    public SubjectExcelListener(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @Override
    public void invoke(SubjectData subjectData, AnalysisContext analysisContext) {

        if(subjectData == null){
            throw new CourseException(20001,"文件数据为空");
        }

        String trimOneSub = subjectData.getOneSubjectName().trim();
        String trimTwoSub = subjectData.getTwoSubjectName().trim();

        Subject existOneSubject = this.existOneSubject(subjectService, trimOneSub);
        String parentId = null;
        if(existOneSubject == null){
            existOneSubject = new Subject();
            existOneSubject.setParentId("0");
            existOneSubject.setTitle(trimOneSub);
            existOneSubject.setSort(analysisContext.readRowHolder().getRowIndex());
            subjectService.save(existOneSubject);
            parentId = existOneSubject.getId();
        }else{
            parentId = existOneSubject.getId();
        }

        Subject existTwoSubject = this.existTwoSubject(subjectService,trimTwoSub,parentId);
        if(existTwoSubject == null){
            existTwoSubject = new Subject();
            existTwoSubject.setParentId(parentId);
            existTwoSubject.setTitle(trimTwoSub);
            existTwoSubject.setSort(analysisContext.readRowHolder().getRowIndex());
            subjectService.save(existTwoSubject);
        }

    }

    private Subject existTwoSubject(SubjectService subjectService, String twoSubjectName, String parentId) {
        QueryWrapper<Subject> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("title",twoSubjectName);
        queryWrapper.eq("parent_id",parentId);
        Subject twoSubject = subjectService.getOne(queryWrapper);
        return twoSubject;
    }

    private Subject existOneSubject(SubjectService subjectService, String oneSubjectName) {
        QueryWrapper<Subject> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("title",oneSubjectName);
        queryWrapper.eq("parent_id",0);;
        Subject oneSubject = subjectService.getOne(queryWrapper);
        return oneSubject;
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
