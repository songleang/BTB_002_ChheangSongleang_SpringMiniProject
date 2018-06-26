package com.example.demo1.services.impl;

import com.example.demo1.services.UploadService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;
@Service
public class UploadServiceImpl implements UploadService {

    @Override
    public String singleFileUpload(MultipartFile file, String folder) {
        if(file==null){
            return null;
        }

        if(folder==null) {
            folder= "";
        }

        File path=new File("/btb"+folder);
        if (!path.exists())
            path.mkdirs();

        String filename=file.getOriginalFilename();
        String extension=filename.substring(filename.lastIndexOf('.')+1);
        System.out.println(filename);
        System.out.println(extension);
//      ------connection img name
        filename = UUID.randomUUID()+"."+extension;

        try {
            Files.copy(file.getInputStream(), Paths.get("/btb"+folder,filename));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return folder+filename;
    }

    @Override
    public List<String> multipleFileUpload(List<MultipartFile> files, String folder) {
        return null;
    }
}
