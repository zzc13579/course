package com.course.edu.service;

import com.course.edu.entity.Subject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.course.edu.vo.SubjectNestedVo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author ShanHen
 * @since 2020-09-11
 */
public interface SubjectService extends IService<Subject> {

    void batchImport(MultipartFile file, SubjectService subjectService);

    List<SubjectNestedVo> nestedList();
}
