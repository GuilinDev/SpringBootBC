package com.guilin.repository;

import com.guilin.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTests {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testSave() {
        User user = new User("testUser", "admin_root", 29);
        userRepository.save(user);
    }

    @Test
    public void testUpdate() {
        User user = new User("testUser", "admin_root", 23);
        user.setId(3L);
        userRepository.update(user);
    }

    @Test
    public void testDelete() {
        userRepository.delete(1L);
    }

    @Test
    public void testQueryOne() {
        User user=userRepository.findById(2L);
        System.out.println("user == " + user.toString());
    }

    @Test
    public void testQueryAll() {
        List<User> users = userRepository.findAll();
        users.stream().forEach((user -> System.out.println("user == " + user)));
    }
}
