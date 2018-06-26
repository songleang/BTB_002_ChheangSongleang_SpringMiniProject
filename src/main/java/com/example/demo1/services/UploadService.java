package com.example.demo1.services;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UploadService {

    String singleFileUpload(MultipartFile file, String folder);

    List<String> multipleFileUpload(List<MultipartFile> files, String folder);

}
