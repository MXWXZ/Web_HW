package com.imwxz.store.service.impl;

import com.imwxz.store.entity.ImageEntity;
import com.imwxz.store.repository.ImageRepository;
import com.imwxz.store.service.IImageService;
import org.bson.types.Binary;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class ImageServiceImpl implements IImageService {
    @Autowired
    private ImageRepository img;

    @Override
    public byte[] findImageById(String id) {
        ImageEntity file = img.findById(new ObjectId(id)).orElse(null);
        if (file != null)
            return file.getImg().getData();
        else
            return null;
    }

    @Override
    public String addImage(MultipartFile file) {
        try {
            ImageEntity obj = new ImageEntity();
            obj.setImg(new Binary(file.getBytes()));
            return img.save(obj).getId().toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
