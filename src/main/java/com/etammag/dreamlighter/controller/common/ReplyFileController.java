package com.etammag.dreamlighter.controller.common;

import com.etammag.dreamlighter.common.entity.Result;
import com.etammag.dreamlighter.service.common.FileService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/reply/file")
@Api(tags = "答题文件相关接口")
public class ReplyFileController extends FileController {


    @Autowired
    public ReplyFileController(FileService fileService) {
        super(fileService);
    }

    @Override
    public String[] typeList() {
        return new String[]{
                ".mp4", ".mov", ".wmv", ".flv", ".avi", ".avchd", "mkv",
                ".jpg", ".png"
        };
    }

    @Override
    public String fileDir() {
        return "reply/";
    }

    @PostMapping
    @PreAuthorize("hasAuthority('KID')")
    @Override
    public Result<String> upload(MultipartFile file) {
        return super.upload(file);
    }

    @GetMapping(value = "/{filename}")
    @PreAuthorize("hasAuthority('LOGIN')")
    @Override
    public void download(@PathVariable String filename, HttpServletResponse response) {
        super.download(filename, response);
    }
}
