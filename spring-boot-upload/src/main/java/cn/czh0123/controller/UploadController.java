package cn.czh0123.controller;

import org.springframework.boot.system.ApplicationHome;
import org.springframework.http.ResponseEntity;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.nio.file.Files;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.InputStreamReader;

@RestController
@CrossOrigin
public class UploadController {

    // 单张 可行
    // @PostMapping(value = "/upload", produces = MediaType.IMAGE_JPEG_VALUE)
    // public ResponseEntity<byte[]> upload(@RequestParam("image") MultipartFile file) throws IOException {
    //     //file校验
    //     if (file.isEmpty()) {
    //         return ResponseEntity.badRequest().body(null);
    //     }

    //     //file重命名 (a: 1.png   b:1.png)
    //     String originalFilename = file.getOriginalFilename(); //原来的图片名

    //     // 上传图片
    //     ApplicationHome applicationHome = new ApplicationHome(this.getClass());
    //     String pre = applicationHome.getDir().getParentFile().getParentFile().getAbsolutePath();
    //     System.out.println(pre);
        
    //     System.out.println(originalFilename);
    //     String path = pre + "/src/main/resources/static/images/test1.jpeg";
    //     try {
    //         file.transferTo(new File(path));
    //     } catch (IOException e) {
    //         e.printStackTrace();
    //     }


    //     //执行python文件
    //     // String command = "python3 " + pre + "/src/main/java/cn/czh0123/controller/pythonFile/test1.py";
    //     String command = "/opt/anaconda3/bin/python " + pre + "/src/main/java/cn/czh0123/controller/pythonFile/GradCAM.py";
    //     System.out.println(command);

    //     Process process = Runtime.getRuntime().exec(command);
    //     BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
    //     String line;
    //     ArrayList<String> arrayString = new ArrayList<>();
    //     while ((line = reader.readLine()) != null) {
    //         arrayString.add(line);
    //         System.out.println(line);
    //     }

    //     System.out.println("=======================");
    //     // String classification = arrayString.get(3);
        

    //     // 返回处理后的图片 单张
    //     // pre output ====== /Users/ndsjr/Documents/GitHub/MengProject/spring-boot-upload
    //     String filePath = pre + "/src/main/resources/static/images/test1.jpeg";

    //     File newFile = new File(filePath);
    //     if (!newFile.exists()) {
    //         throw new FileNotFoundException("Image not found");
    //     }
    //     byte[] imageBytes = Files.readAllBytes(newFile.toPath());
    //     HttpHeaders headers = new HttpHeaders();
    //     headers.setContentType(MediaType.IMAGE_JPEG);
    //     headers.setContentLength(imageBytes.length);
    //     return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);
    // }


    // 多张
    @PostMapping(value = "/upload", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<byte[]> upload_s(@RequestParam("image") MultipartFile file) throws IOException {
        // file校验
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body(null);
        }

        // file重命名 (a: 1.png b:1.png)
        String originalFilename = file.getOriginalFilename(); // 原来的图片名

        // 上传图片
        ApplicationHome applicationHome = new ApplicationHome(this.getClass());
        String pre = applicationHome.getDir().getParentFile().getParentFile().getAbsolutePath();
        // System.out.println(pre);

        System.out.println("info1: "+originalFilename);
        String path = pre + "/src/main/resources/static/images/test1.jpeg";
        try {
            file.transferTo(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 执行python文件
        // String command = "python3 " + pre +
        // "/src/main/java/cn/czh0123/controller/pythonFile/test1.py";
        String command = "/opt/anaconda3/bin/python " + pre
                + "/src/main/java/cn/czh0123/controller/pythonFile/GradCAM.py";
        System.out.println(command);

        Process process = Runtime.getRuntime().exec(command);
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line;
        ArrayList<String> arrayString = new ArrayList<>();
        while ((line = reader.readLine()) != null) {
            arrayString.add(line);
            System.out.println(line);
        }

        System.out.println("=======================");

        // Save classification result to txt
        String classification = arrayString.get(3);
        String txtPath = pre + "/src/main/resources/";
        try {
            FileWriter myWriter = new FileWriter(txtPath + "clfResult.txt");
            myWriter.write(classification);
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        // ======= ***** where you put your prompt for chatGPT that initializes chat.txt
        try {
            FileWriter myWriter = new FileWriter(txtPath + "chat.txt");
            myWriter.write("You are an AI research assistant. You use a tone that is technical and scientific. All your response should be in the same line.\n");
            myWriter.close();
            System.out.println("chat txt file initilization succeed ");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        
        // 返回处理后的图片 多张
        // Add more images to the ArrayList
        // !!!!!!!!!!!!! 注意 这两个文件名字不能相同！！！！！！！！！！！！！！！
        ArrayList<File> filesList = new ArrayList<File>();
        String filePath1 = pre + "/src/main/resources/static/images/GCAM_imgwithheat.jpg";
        String filePath2 = pre + "/src/main/resources/static/images/GCAM++_imgwithheat.jpg";

        // String filePath1 = pre + "/src/main/resources/static/images/test1.jpeg";
        // String filePath2 = pre + "/src/main/resources/static/images/test2.jpeg";

        File newFile1 = new File(filePath1);
        File newFile2 = new File(filePath2);

        if (!newFile1.exists()) {
            throw new FileNotFoundException("Image not found");
        }
        if (!newFile2.exists()) {
            throw new FileNotFoundException("Image not found");
        }
        filesList.add(newFile1);
        filesList.add(newFile2);




        // 将多张图片打包成zip文件
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ZipOutputStream zos = new ZipOutputStream(baos);

        for (File imageFile : filesList) {
            FileInputStream fileInputStream = new FileInputStream(imageFile);
            ZipEntry zipEntry = new ZipEntry(imageFile.getName());
            zos.putNextEntry(zipEntry);
            byte[] bytes = new byte[1024];
            int length;
            while ((length = fileInputStream.read(bytes)) >= 0) {
                zos.write(bytes, 0, length);
            }
            fileInputStream.close();
            zos.closeEntry();
        }
        zos.close();

        // 将zip文件返回给前端
        byte[] zipBytes = baos.toByteArray();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentLength(zipBytes.length);
        headers.setContentDispositionFormData("attachment", "images.zip");

        return new ResponseEntity<>(zipBytes, headers, HttpStatus.OK);
    }
}
