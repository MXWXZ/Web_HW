package com.imwxz.store.controller;

import com.imwxz.store.service.IImageService;
import com.imwxz.store.util.RetMessage;
import com.imwxz.store.util.jwt.AdminToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class ImageController {
    @Autowired
    IImageService img;

    @GetMapping(value = "/image/{imgId}", produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseBody
    public byte[] getImage(@PathVariable String imgId) {
        return img.findImageById(imgId);
    }

    @PostMapping("/api/images")
    @AdminToken
    @ResponseBody
    public RetMessage uploadImage(@RequestParam(value = "bookImg") MultipartFile file) {
        if (file.isEmpty())
            return new RetMessage(1, "Please upload an image.");

        RetMessage ret = new RetMessage();
        String id = img.addImage(file);
        if (id == null) {
            ret.setCode(2);
            ret.setMsg("Upload failed!");
        } else {
            ret.setData(id);
        }
        return ret;
    }

}
