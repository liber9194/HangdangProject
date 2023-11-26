package com.example.hangdang.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.example.hangdang.config.S3Component;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.InputStream;

@Component
@RequiredArgsConstructor
public class AwsS3UploadService implements UploadService{

    private final AmazonS3 amazonS3;
    private final S3Component component;

    @Override
    public  void  uploadFile(InputStream inputStream, ObjectMetadata objectMeTadata, String fileName){
        amazonS3.putObject(new PutObjectRequest(component.getBucket(),fileName,inputStream,objectMeTadata).withCannedAcl(CannedAccessControlList.PublicRead));
    }

    @Override
    public  String getFileUrl(String fileName){
        return amazonS3.getUrl(component.getBucket(),fileName).toString();
    }
}
