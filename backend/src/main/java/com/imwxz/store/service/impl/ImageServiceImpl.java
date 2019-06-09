package com.imwxz.store.service.impl;

import com.imwxz.store.entity.ImageEntity;
import com.imwxz.store.repository.ImageRepository;
import com.imwxz.store.service.IImageService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
