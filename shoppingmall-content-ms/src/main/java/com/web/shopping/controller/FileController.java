package com.web.shopping.controller;

import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
//@CrossOrigin("*")
public class FileController {

    /**
     * minio:
     *   bucketName: mall # 存储桶名字
     *   endpoint: 127.0.0.1 #桶所在节点ip
     *   port: 9000      # web管理服务访问端口
     *   secure: false  #false为http   true为 https
     *   accessKey: minioadmin #登录账号
     *   secretKey: minioadmin #密码
     */
    //获取minio的参数
    @Value("${minio.bucketName}")
    private String bucketName;
    @Value("${minio.endpoint}")
    private String endpoint;
    @Value("${minio.port}")
    private Integer port;
    @Value("${minio.secure}")
    private Boolean secure;
    @Value("${minio.accessKey}")
    private String accessKey;
    @Value("${minio.secretKey}")
    private String secretKey;

    @PostMapping("/uploadFile")
    public String uploadFile(MultipartFile file){
        //创建minio客户端
        MinioClient minioClient = MinioClient.builder().endpoint(endpoint,port,secure).credentials(accessKey, secretKey).build();
        String objectUrl = "";
        try {
            boolean bucketExists = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
            if (bucketExists){
                System.out.println("Bucket already exist.");
            }else{
                //程序创建存储桶
                minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());

            }
            //设置文件存储对象
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            String filename = file.getOriginalFilename();
            //存储对象名,文件路径
            String objectName = sdf.format(new Date())+"/"+filename;

            //把二进制文件保存存储桶中
            minioClient.putObject(PutObjectArgs.builder()
                    .bucket(bucketName)
                    .object(objectName)
                    .stream(file.getInputStream(),file.getSize(),-1).build()
                    );
            objectUrl = minioClient.getObjectUrl(bucketName, objectName);
            System.out.println("文件路径："+objectUrl);
        }catch (Exception e) {
            e.printStackTrace();
        }

        return objectUrl;
    }
}
