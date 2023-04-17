package cn.czh0123.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.springframework.boot.system.ApplicationHome;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import com.aliyun.oss.common.utils.HttpUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

@RestController
@RequestMapping
@CrossOrigin
public class GPTController {
    /**
     * @return
     * @throws IOException
     */
    @GetMapping(value = "/desc")
    public ResponseEntity<String[]> getImg() throws IOException {

        // 上传图片
        ApplicationHome applicationHome = new ApplicationHome(this.getClass());
        String pre = applicationHome.getDir().getParentFile().getParentFile().getAbsolutePath();
        // output ====== /Users/ndsjr/Documents/GitHub/MengProject/spring-boot-upload
        String filePath = pre + "/src/main/java/cn/czh0123/controller/pythonFile/Outputs/test.txt";


        File file = new File(filePath);
        if (!file.exists()) {
            throw new FileNotFoundException("Image not found");
        }

        String[] lines = null;
        try {
            lines = Files.readAllLines(Paths.get(filePath)).toArray(new String[0]);
            System.out.println("Reading lines from get!");
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.ALL);
        headers.setContentLength(lines.length);

        return new ResponseEntity<>(lines, headers, HttpStatus.OK);
    }
}
