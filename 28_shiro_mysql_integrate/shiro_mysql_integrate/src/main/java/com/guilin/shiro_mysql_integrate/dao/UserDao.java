package com.guilin.shiro_mysql_integrate.dao;

import com.guilin.shiro_mysql_integrate.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserDao extends CrudRepository<User, Long> {

    public User findUserByUsername(String username);

}
