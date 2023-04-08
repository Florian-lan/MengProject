package cn.czh0123.controller;

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

    /**
     * @return
     * @throws IOException
     */
    @GetMapping(value = "/image", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> getImg() throws IOException {

        // 上传图片
        ApplicationHome applicationHome = new ApplicationHome(this.getClass());
        String pre = applicationHome.getDir().getParentFile().getParentFile().getAbsolutePath();
        // output ======    /Users/ndsjr/Documents/GitHub/MengProject/spring-boot-upload
        String filePath = pre + "/src/main/resources/static/images/test1.jpeg";
        
        File file = new File(filePath);
        if (!file.exists()) {
            throw new FileNotFoundException("Image not found");
        }
        byte[] imageBytes = Files.readAllBytes(file.toPath());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        headers.setContentLength(imageBytes.length);
        return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);
    }
    
    /* 
    提供一个可行的React读取 参考
    * function App() {
    * const [imageSrc, setImageSrc] = useState(null);
    * 
    * useEffect(() => {
    * fetch('image')
    * .then((response) => response.blob())
    * .then((blob) => {
    * const objectUrl = URL.createObjectURL(blob);
    * setImageSrc(objectUrl);
    * })
    * .catch((error) => {
    * console.error(error);
    * });
    * }, []);
    * 
    * return (
    * <img src={imageSrc} />
    * );
    * }
    * 
    * export default App;
    */

    // 可以成功显示 可以将上面注释 然后修改这里的 imagePath 进行测试
    // @GetMapping(value = "/image", produces = MediaType.IMAGE_JPEG_VALUE)
    // public ResponseEntity<byte[]> getImage() throws IOException {
    //     String imagePath = "/Users/ndsjr/Documents/GitHub/MengProject/spring-boot-upload/src/main/resources/static/images/test1.jpeg";
    //     File file = new File(imagePath);
    //     if (!file.exists()) {
    //         throw new FileNotFoundException("Image not found: ");
    //     }
    //     byte[] imageBytes = Files.readAllBytes(file.toPath());
    //     HttpHeaders headers = new HttpHeaders();
    //     headers.setContentType(MediaType.IMAGE_JPEG);
    //     headers.setContentLength(imageBytes.length);
    //     return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);
    // }
}

