package cn.czh0123.controller;

import org.springframework.boot.system.ApplicationHome;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.util.UUID;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@RestController
@CrossOrigin
public class UploadController {

    @PostMapping("/upload")
    public ResponseEntity<String> upload(@RequestParam("image") MultipartFile file) throws IOException {
        //file校验
        if (file.isEmpty()) {
//            return "图片上传失败";
            return ResponseEntity.badRequest().body("No file chosen");
        }

        //file重命名 (a: 1.png   b:1.png)
        String originalFilename = file.getOriginalFilename(); //原来的图片名

        // 上传图片
        ApplicationHome applicationHome = new ApplicationHome(this.getClass());
        String pre = applicationHome.getDir().getParentFile().getParentFile().getAbsolutePath();
        System.out.println(pre);
        
        System.out.println(originalFilename);
        String path = pre + "test1.jpeg";
        try {
            file.transferTo(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }


        //执行python文件
        // String command = "python3 " + pre + "/src/main/java/cn/czh0123/controller/pythonFile/test1.py";
        String command = "/opt/anaconda3/bin/python " + pre + "/src/main/java/cn/czh0123/controller/pythonFile/GradCAM.py";
        System.out.println(command);
        // String command = "pwd";
        
        Process process = Runtime.getRuntime().exec(command);
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }

        System.out.println("=======================");
        
        return ResponseEntity.ok(originalFilename);
    }

//    @GetMapping
//    public String upload(MultipartFile file) {
//
//
//        return "";
//    }
}
