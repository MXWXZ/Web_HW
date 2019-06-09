package com.imwxz.store.entity;

import org.bson.types.Binary;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

@Document(collection = "book_img")
public class ImageEntity {
    @Id
    private ObjectId id;
    private Binary img;

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public Binary getImg() {
        return img;
    }

    public void setImg(Binary img) {
        this.img = img;
    }
}
