package com.course.edu.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.course.common.constants.ResultCodeEnum;
import com.course.common.exception.CourseException;
import com.course.edu.entity.Subject;
import com.course.edu.entity.SubjectData;
import com.course.edu.mapper.SubjectMapper;
import com.course.edu.service.SubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.course.edu.util.SubjectExcelListener;
import com.course.edu.vo.SubjectNestedVo;
import com.course.edu.vo.SubjectVo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author ShanHen
 * @since 2020-09-11
 */
@Service
public class SubjectServiceImpl extends ServiceImpl<SubjectMapper, Subject> implements SubjectService {

    @Override
    public void batchImport(MultipartFile file, SubjectService subjectService) {
        try {
            InputStream fileInputStream = file.getInputStream();
            EasyExcel.read(fileInputStream, SubjectData.class,new SubjectExcelListener(subjectService)).sheet().doRead();
        } catch (Exception e) {
            throw new CourseException(ResultCodeEnum.EXCEL_DATA_IMPORT_ERROR);
        }

    }

    @Override
    public List<SubjectNestedVo> nestedList() {

        ArrayList<SubjectNestedVo> subjectNestedVoArrayList = new ArrayList<>();

        QueryWrapper<Subject> queryWrapperOne = new QueryWrapper<>();
        queryWrapperOne.eq("parent_id",0);
        queryWrapperOne.orderByAsc("sort","id");

        QueryWrapper<Subject> queryWrapperTwo = new QueryWrapper<>();
        queryWrapperTwo.ne("parent_id",0);
        queryWrapperTwo.orderByAsc("sort","id");

        List<Subject> subjectOne = baseMapper.selectList(queryWrapperOne);
        for (int i = 0; i < subjectOne.size(); i++){
            Subject subject = subjectOne.get(i);
            SubjectNestedVo subjectNestedVo = new SubjectNestedVo();
            BeanUtils.copyProperties(subject,subjectNestedVo);
            subjectNestedVoArrayList.add(subjectNestedVo);

            ArrayList<SubjectVo> subjectVoArrayList = new ArrayList<>();
            List<Subject> subjectTwo = baseMapper.selectList(queryWrapperTwo);

            for(int j = 0; j < subjectTwo.size(); j++){
                Subject subjects = subjectTwo.get(j);
                if(subjects.getParentId().equals(subject.getId())){
                    SubjectVo subjectVo = new SubjectVo();
                    BeanUtils.copyProperties(subjects,subjectVo);
                    subjectVoArrayList.add(subjectVo);
                }
            }
            subjectNestedVo.setChildren(subjectVoArrayList);
        }

        return subjectNestedVoArrayList;
    }
}
