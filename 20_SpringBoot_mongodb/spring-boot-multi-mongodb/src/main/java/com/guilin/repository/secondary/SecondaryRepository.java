package com.guilin.repository.secondary;

import com.guilin.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author neo
 */
public interface SecondaryRepository extends MongoRepository<User, String> {
}
