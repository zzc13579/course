package com.course.oss.service.Impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.CannedAccessControlList;
import com.course.common.constants.ResultCodeEnum;
import com.course.common.exception.CourseException;
import com.course.oss.service.FileService;
import com.course.oss.util.ConstantPropertiesUtil;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.UUID;

/**
 * @auther shanhen
 * @create 2020-09-11 14:58
 */
@Service
public class FileServiceImpl implements FileService {
    @Override
    public String upload(MultipartFile file) {

        // Endpoint以杭州为例，其它Region请按实际情况填写。
        String endpoint = ConstantPropertiesUtil.END_POINT;
// 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建RAM账号。
        String accessKeyId = ConstantPropertiesUtil.ACCESS_KEY_ID;
        String accessKeySecret = ConstantPropertiesUtil.ACCESS_KEY_SECRET;
        String bucketName = ConstantPropertiesUtil.BUCKET_NAME;
        String fileHost = ConstantPropertiesUtil.FILE_HOST;

        String uploadUrl = null;
        try {
            OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
            if(!ossClient.doesBucketExist(bucketName)){
                ossClient.createBucket(bucketName);
                ossClient.setBucketAcl(bucketName, CannedAccessControlList.PublicRead);
            }

            InputStream fileInputStream = file.getInputStream();

            String fileTime = new DateTime().toString("yyyy/MM/dd");
            String originalFilename = file.getOriginalFilename();
            String fileName = UUID.randomUUID().toString();
            String fileType = originalFilename.substring(originalFilename.lastIndexOf("."));

            String newName = fileName + fileType;

            String fileUrl = fileHost + "/" + fileTime + "/" + newName;

            ossClient.putObject(bucketName, fileUrl, fileInputStream);

            // 关闭OSSClient。
            ossClient.shutdown();

            uploadUrl = "http://" + bucketName + "." + endpoint + "/" + fileUrl;
        } catch (Exception e) {
            throw new CourseException(ResultCodeEnum.FILE_UPLOAD_ERROR);
        }


        return uploadUrl;
    }
}
