package com.guilin.mapper.two;

import com.guilin.model.User;

import java.util.List;

public interface User2Mapper {

    List<User> getAll();

    User getOne(Long id);

    void insert(User user);

    void update(User user);

    void delete(Long id);

}
