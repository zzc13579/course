package com.course.common.exception;

import com.course.common.constants.ResultCodeEnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @auther shanhen
 * @create 2020-09-11 14:40
 */
@Data
@Api(value = "全局异常")
public class CourseException extends RuntimeException{

    @ApiModelProperty(value = "状态码")
    private Integer code;

    public CourseException(Integer code, String message){
        super(message);
        this.code = code;
    }

    public CourseException(ResultCodeEnum resultCodeEnum){
        super(resultCodeEnum.getMessage());
        this.code = resultCodeEnum.getCode();
    }
}
