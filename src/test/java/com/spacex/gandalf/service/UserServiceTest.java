package com.spacex.gandalf.service;

import com.spacex.gandalf.dto.UserDTO;
import com.spacex.gandalf.dto.pagination.PaginationDTO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

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

    @Test
    public void getByPagination() {
        PaginationDTO paginationDTO = new PaginationDTO();
        paginationDTO.setPageNum(1);
        paginationDTO.setSize(10);
        List<UserDTO> userDTOs = userService.getByPagination(paginationDTO);
        Assert.assertNotNull(userDTOs);
        Assert.assertTrue(userDTOs.size() > 0);
    }

    @Test
    @Transactional
    public void create() {
        UserDTO userDTO = new UserDTO();
        userDTO.setName("gandalf");
        userDTO.setAvatar("avatar");

        UserDTO result = userService.create(userDTO);

        Assert.assertNotNull(result);
        Assert.assertEquals(result.getName(), userDTO.getName());
        Assert.assertEquals(result.getAvatar(), userDTO.getAvatar());
    }

    @Test
    @Transactional
    public void update() {
        UserDTO userDTO = new UserDTO();
        Long userId = 30008L;
        userDTO.setId(userId);
        userDTO.setName("gandalf-2");

        UserDTO userDTO1 = userService.getById(userId);
        System.out.println(userDTO1);
        UserDTO result = userService.update(userDTO);
        Assert.assertNotNull(result);
        Assert.assertEquals(result.getName(), userDTO.getName());
    }

    @Test
    @Transactional
    public void delete() {
        Long userId = 30008L;
        userService.delete(userId);
        UserDTO userDTO = userService.getById(userId);
        Assert.assertNull(userDTO);
    }
}