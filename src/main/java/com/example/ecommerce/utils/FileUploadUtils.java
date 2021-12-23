package com.example.ecommerce.utils;

import java.io.*;
import java.nio.file.*;
import java.time.Instant;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileUploadUtils {
    public static String uploadDir = System.getProperty("user.dir")+"/src/main/resources/uploads";

    public void saveFile(MultipartFile imageFile ,String fileName) {
        Path path = Paths.get(uploadDir,fileName);
        try{
            Files.write(path,imageFile.getBytes());
            System.out.println("yay");
        }catch (IOException exception){
            exception.printStackTrace();
        }
    }

    public void deleteFile(String filename){
        Path fileToDeletePath = Paths.get(uploadDir,filename);
        try{
            Files.delete(fileToDeletePath);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
