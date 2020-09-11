package com.course.edu.service;

import com.course.edu.entity.Subject;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

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
}
