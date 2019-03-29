package com.spacex.gandalf.service;

import com.spacex.gandalf.dto.UserDTO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Resource
    private UserService userService;

    @Test
    public void getById() {
        Long userId = 1L;
        UserDTO userDTO = userService.getById(userId);
        Assert.assertNotNull(userDTO);
        Assert.assertEquals(userDTO.getId(), userId);
    }
}