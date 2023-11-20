package com.etammag.dreamlighter.service.common;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileService {

    String save(MultipartFile file, String fileDir);

    byte[] get(String filename, String fileDir) throws IOException;
}
