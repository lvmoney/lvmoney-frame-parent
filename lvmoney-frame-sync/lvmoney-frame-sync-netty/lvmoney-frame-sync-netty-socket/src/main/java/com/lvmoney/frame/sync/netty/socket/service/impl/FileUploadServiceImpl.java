package com.lvmoney.frame.sync.netty.socket.service.impl;

import com.lvmoney.frame.sync.netty.socket.model.vo.ResponseJson;
import com.lvmoney.frame.sync.netty.socket.service.FileUploadService;
import com.lvmoney.frame.sync.netty.socket.util.FileUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import static com.lvmoney.frame.base.core.constant.BaseConstant.DASH_LINE;

@Service
public class FileUploadServiceImpl implements FileUploadService {

    private final static String SERVER_URL_PREFIX = "http://localhost:8080/WebSocket/";
    private final static String FILE_STORE_PATH = "UploadFile";

    @Override
    public ResponseJson upload(MultipartFile file, HttpServletRequest request) {
        // 重命名文件，防止重名
        String filename = getRandomUUID();
        String suffix = "";
        String originalFilename = file.getOriginalFilename();
        String fileSize = FileUtils.getFormatSize(file.getSize());
        // 截取文件的后缀名
        if (originalFilename.contains(".")) {
            suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
        }
        filename = filename + suffix;
        String prefix = request.getSession().getServletContext().getRealPath("/") + FILE_STORE_PATH;
        System.out.println("存储路径为:" + prefix + "\\" + filename);
        Path filePath = Paths.get(prefix, filename);
        try {
            File file1 = new File(prefix + File.separator);
            if (!file1.exists()) {
                file1.mkdirs();
            }
            Files.copy(file.getInputStream(), filePath);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseJson().error("文件上传发生错误！");
        }
        return new ResponseJson().success()
                .setData("originalFilename", originalFilename)
                .setData("fileSize", fileSize)
                .setData("fileUrl", SERVER_URL_PREFIX + FILE_STORE_PATH + "\\" + filename);
    }

    private String getRandomUUID() {
        return UUID.randomUUID().toString().replace(DASH_LINE, "");
    }

}
