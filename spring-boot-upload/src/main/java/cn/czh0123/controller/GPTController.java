package cn.czh0123.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

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

    @GetMapping(value = "/desc")
    public ResponseEntity<List<String>> getText() throws IOException {

        // 上传图片
        ApplicationHome applicationHome = new ApplicationHome(this.getClass());
        String pre = applicationHome.getDir().getParentFile().getParentFile().getAbsolutePath();
        // output ====== /Users/ndsjr/Documents/GitHub/MengProject/spring-boot-upload
        String filePath = pre + "/src/main/java/cn/czh0123/controller/pythonFile/Outputs/test.txt";

        List<String> readAllLines = null;
        try {
            readAllLines = Files.readAllLines(Paths.get(filePath));
            System.out.println(readAllLines.get(0));
            System.out.println("Reading lines from get!");
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(readAllLines, HttpStatus.OK);
    }

    @PostMapping(value = "/chat")
    public ResponseEntity<String> chat(@RequestBody String inputQuestion) throws IOException {
   
        inputQuestion = "The answer to question: " + inputQuestion + " is 1";
        return new ResponseEntity<>(inputQuestion, HttpStatus.OK);
    }
}
