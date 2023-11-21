package com.etammag.dreamlighter.controller.common;

import com.etammag.icommon.entity.Result;
import com.etammag.dreamlighter.service.common.FileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Objects;

@Slf4j
public abstract class FileController {

    public abstract String[] typeList();

    public abstract String fileDir();

    private final FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    public Result<String> upload(MultipartFile file) {
        String filename = file.getOriginalFilename();
        String fileType = filename.substring(filename.lastIndexOf("."));
        for (String type : typeList())
            if (Objects.equals(fileType, type)) {
                String encode = fileService.save(file, fileDir());
                if (encode == null) return Result.error("上传失败");
                else return Result.success(encode);
            }
        return Result.error("只能上传媒体文件");
    }

    public void download(String filename, HttpServletResponse response) {
        response.reset();
        response.setCharacterEncoding("UTF-8");
        response.addHeader("Content-Disposition", "attachment;filename=" + filename);
        response.setContentType("application/octet-stream");
        try {
            OutputStream os = new BufferedOutputStream(response.getOutputStream());
            os.write(fileService.get(filename, fileDir()));
            os.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
