package com.etammag.dreamlighter.service.common.impl;

import com.etammag.dreamlighter.service.common.FileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.UUID;

@Service
@Slf4j
public class FileServiceImpl implements FileService {

    @Value("${dream-lighter.sys.file-save-path}")
    private String FILE_PATH;

    @Override
    public String save(MultipartFile file, String fileDir) {
        if (file.isEmpty()) return null;

        String filePath = FILE_PATH + fileDir;
        File temp = new File(filePath);
        if (!temp.exists()) temp.mkdirs();

        String originalFilename = file.getOriginalFilename();
        int i = originalFilename.lastIndexOf(".");
        String filetype = originalFilename.substring(i);

        String localFileName = UUID.randomUUID() + filetype;
        File localFile = new File(filePath + localFileName);

        try {
            file.transferTo(localFile);
        } catch (IOException e) {
            log.warn("文件报错失败", e);
            return null;
        }
        return localFileName;
    }

    @Override
    public byte[] get(String filename, String fileDir) throws IOException {
        File file = new File(FILE_PATH + fileDir + filename);
        if (!file.exists()) throw new FileNotFoundException();
        try (FileInputStream fis = new FileInputStream(file)) {
            byte[] bytes = new byte[fis.available()];
            fis.read(bytes);
            return bytes;
        }
    }
}
