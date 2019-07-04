package com.guilin.repository;

import com.guilin.model.User;

import java.util.List;

public interface UserRepository {

    int save(User user);

    int update(User user);

    int delete(long id);

    List<User> findAll();

    User findById(long id);
}
