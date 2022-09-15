package com.blocdao.project.controller;

import com.blocdao.project.exception.CustomException;
import com.blocdao.project.exception.ErrorCode;
import com.blocdao.project.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/files")
public class FIleController {

    private final FileService fileService;

    @PostMapping()
    public List<String> uploadFile(@RequestParam("files") List<MultipartFile> files) throws IOException {
        if (files.isEmpty()) {
            throw new CustomException(ErrorCode.NOT_FOUND_FILE);
        }
        return fileService.uploadFiles(files);
    }
}
