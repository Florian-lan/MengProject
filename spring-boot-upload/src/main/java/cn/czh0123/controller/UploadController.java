package cn.czh0123.controller;

import org.springframework.boot.system.ApplicationHome;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
@CrossOrigin
public class UploadController {

    @PostMapping("/upload")
    public ResponseEntity<String> upload(@RequestParam("image") MultipartFile file) {
         if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("No file chosen");
        }

        //file重命名 (a: 1.png   b:1.png)
        String originalFilename = file.getOriginalFilename(); //原来的图片名

        // 上传图片
        ApplicationHome applicationHome = new ApplicationHome(this.getClass());
        String pre = applicationHome.getDir().getParentFile().getParentFile().getAbsolutePath() +
                "/src/main/resources/static/images/"; 
        
        System.out.println(originalFilename);
        String path = pre + "test1.jpeg";
        try {
            file.transferTo(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }

        
        return ResponseEntity.ok(originalFilename);
    }

//    @GetMapping
//    public String upload(MultipartFile file) {
//
//
//        return "";
//    }
}
