package com.blocdao.project.controller;

import com.blocdao.project.exception.CustomException;
import com.blocdao.project.exception.ErrorCode;
import com.blocdao.project.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("api/files")
@RequiredArgsConstructor
public class FileController {

    private final FileService fileService;

    @PostMapping()
    public List<String> uploadFiles(@RequestParam @Valid List<MultipartFile> files) throws IOException {
        if (files.isEmpty()) {
            throw new CustomException(ErrorCode.NOT_FOUND_FILE);
        }

        return fileService.uploadFiles(files);
    }

    @GetMapping("/{imageUrl}")
    public byte[] getFile(@PathVariable String imageUrl) {
        return fileService.getFile(imageUrl);
    }
}
