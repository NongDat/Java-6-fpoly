package com.poly.j6d8_asm_ph18618.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;

public interface UploadService {
    File save(MultipartFile file, String folder);
}
