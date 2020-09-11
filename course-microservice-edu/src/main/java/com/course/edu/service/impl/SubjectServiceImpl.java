package com.course.edu.service.impl;

import com.alibaba.excel.EasyExcel;
import com.course.common.constants.ResultCodeEnum;
import com.course.common.exception.CourseException;
import com.course.edu.entity.Subject;
import com.course.edu.entity.SubjectData;
import com.course.edu.mapper.SubjectMapper;
import com.course.edu.service.SubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.course.edu.util.SubjectExcelListener;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

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
}
