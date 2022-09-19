package com.blocdao.project.service;

import com.blocdao.project.exception.CustomException;
import com.blocdao.project.exception.ErrorCode;
import com.google.cloud.storage.Bucket;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class FileService {

    private final Bucket bucket;

    public List<String> uploadFiles(List<MultipartFile> files) {

        List<String> urls = new ArrayList<>();

        for (MultipartFile file : files) {
            String blob = "files/";

            try {
                String url = UUID.randomUUID().toString();
                bucket.create(blob + url, file.getBytes(), file.getContentType());

                urls.add(url);

            } catch (IOException e) {
                log.error("file upload faild", e);
                throw new CustomException(ErrorCode.IMAGE_UPLOAD_FAILED);
            }
        }

        return urls;
    }

    public byte[] getFile(String imageUrl) {
        return bucket.get("files/" + imageUrl).getContent();
    }
}