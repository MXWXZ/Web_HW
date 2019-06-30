package com.imwxz.store.service;

import org.springframework.web.multipart.MultipartFile;

public interface IImageService {
    public byte[] findImageById(String id);

    public String addImage(MultipartFile file);
}
