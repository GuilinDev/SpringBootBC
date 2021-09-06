package com.guilin.repository.secondary;

import com.guilin.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SecondaryRepository extends MongoRepository<User, String> {
}
