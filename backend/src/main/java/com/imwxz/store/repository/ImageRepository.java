package com.imwxz.store.repository;

import com.imwxz.store.entity.ImageEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ImageRepository extends MongoRepository<ImageEntity, ObjectId> {
}
