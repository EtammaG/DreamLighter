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
@RequestMapping("/pic")
@Api(tags = "获取图片相关接口")
public class PicController extends FileController {

    @Autowired
    public PicController(FileService fileService) {
        super(fileService);
    }

    @Override
    public String[] typeList() {
        return new String[]{
                ".jpg", ".png"
        };
    }

    @Override
    public String fileDir() {
        return "pic/";
    }

    @PostMapping
    @PreAuthorize("hasAuthority('LOGIN')")
    @Override
    public Result<String> upload(MultipartFile file) {
        return super.upload(file);
    }

    @GetMapping("/{filename}")
    @PreAuthorize("hasAuthority('LOGIN')")
    @Override
    public void download(@PathVariable String filename, HttpServletResponse response) {
        super.download(filename, response);
    }
}
