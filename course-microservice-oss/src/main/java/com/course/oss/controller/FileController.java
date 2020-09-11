package com.course.oss.controller;

import com.course.common.vo.R;
import com.course.oss.service.FileService;
import com.course.oss.util.ConstantPropertiesUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @auther shanhen
 * @create 2020-09-11 15:46
 */
@Api(description = "文件上传管理")
@CrossOrigin
@RestController
@RequestMapping(value = "/admin/oss/file")
public class FileController {

    @Autowired
    FileService fileService;

    @ApiOperation(value = "文件上传")
    @PostMapping("upload")
    public R upload(
            @ApiParam(name = "file", value = "文件", required = true)
            @RequestParam("file") MultipartFile file,
            @ApiParam(name = "host",value = "文件路径",required = false)
            @RequestParam("host") String host
            ){

        if(!StringUtils.isEmpty(host)){
            ConstantPropertiesUtil.FILE_HOST = host;
        }
        String upload = fileService.upload(file);
        return R.ok().message("文件上传成功").data("url",upload);
    }
}
