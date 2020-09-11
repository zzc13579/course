package com.course.edu.vo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @auther shanhen
 * @create 2020-09-11 19:51
 */
@Data
public class SubjectNestedVo {
    private String id;
    private String title;
    private List<SubjectVo> children = new ArrayList<>();
}
