package com.guilin.repository.primary;

import com.guilin.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PrimaryRepository extends MongoRepository<User, String> {
}
