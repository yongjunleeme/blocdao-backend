package com.blocdao.project.service;

import com.google.cloud.storage.Blob;
import com.google.cloud.storage.Bucket;
import com.google.firebase.FirebaseApp;
import com.google.firebase.cloud.StorageClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileService {

    private final Bucket bucket;

    public List<String> uploadFiles(List<MultipartFile> files) throws IOException {
        List<String> fileUrls = new ArrayList<>();

        String blob = "files/";

        for (MultipartFile file : files) {
            String url = blob + UUID.randomUUID().toString();
            bucket.create(url, file.getBytes());
            fileUrls.add("/" + url);
        }
        return fileUrls;
    }
//    public String uploadFiles(MultipartFile file, String nameFile) throws IOException {
//
//        Bucket bucket = StorageClient.getInstance(firebaseApp).bucket("cloudwi-894c9.appspot.com");
//        InputStream content = new ByteArrayInputStream(file.getBytes());
//        Blob blob = bucket.create(nameFile, content, file.getContentType());
//
//        return blob.getMediaLink();
//    }

}
