package com.course.common.handler;

import com.course.common.exception.CourseException;
import com.course.common.vo.R;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @auther shanhen
 * @create 2020-09-11 14:44
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CourseException.class)
    @ResponseBody
    public R error(CourseException e){
        e.printStackTrace();
        return R.error().message(e.getMessage()).code(e.getCode());
    }
}
