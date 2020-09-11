package com.course.edu.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @auther shanhen
 * @create 2020-09-11 23:16
 */
@Data
public class VideoVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    private String title;
    private Boolean free;
}
