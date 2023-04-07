package cn.czh0123.controller;

import org.springframework.boot.system.ApplicationHome;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.aliyun.oss.common.utils.HttpHeaders;
import com.aliyun.oss.common.utils.HttpUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping
@CrossOrigin
public class ImageController {
    // @GetMapping(value = "/image",produces = MediaType.IMAGE_JPEG_VALUE)
    // @ResponseBody
    // public byte[] test() throws Exception {
    //     File file = new File("/Users/ndsjr/Music/BaiduDisk/SpringBoot文件上传/spring-boot-upload/src/main/resources/static/images/65eff2509b8344a59187f6c69dfdf606.jpeg");
    //     FileInputStream inputStream = new FileInputStream(file);
    //     byte[] bytes = new byte[inputStream.available()];
    //     inputStream.read(bytes, 0, inputStream.available());
    //     return bytes;
    // }

    @GetMapping("/image")
    public ResponseEntity<byte[]> getImg() throws IOException {

        // 上传图片
        ApplicationHome applicationHome = new ApplicationHome(this.getClass());
        String pre = applicationHome.getDir().getParentFile().getParentFile().getAbsolutePath();
        // output ======    /Users/ndsjr/Documents/GitHub/MengProject/spring-boot-upload
        String filePath = pre + "/src/main/resources/static/images/test1.jpeg";
        
        File file = new File(filePath);
        try (FileInputStream inputStream = new FileInputStream(file)) {
            byte[] bytes = new byte[inputStream.available()];
            return ResponseEntity.ok(bytes);
        }
    }

}

