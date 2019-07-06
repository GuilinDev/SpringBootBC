package com.guilin.mapper;

import com.guilin.enums.UserSexEnum;
import com.guilin.mapper.test1.User1Mapper;
import com.guilin.mapper.test2.User2Mapper;
import com.guilin.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTest {
    @Autowired
    private User1Mapper user1Mapper;
    @Autowired
    private User2Mapper user2Mapper;

    @Test
    public void testInsert() throws Exception {
        user1Mapper.insert(new User("aaa111", "a123456", UserSexEnum.MAN));
        user1Mapper.insert(new User("bbb111", "b123456", UserSexEnum.WOMAN));
        user2Mapper.insert(new User("ccc222", "b123456", UserSexEnum.MAN));
    }
}
