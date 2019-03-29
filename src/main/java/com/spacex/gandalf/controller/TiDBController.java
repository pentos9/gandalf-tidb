package com.spacex.gandalf.controller;

import com.spacex.gandalf.dto.UserDTO;
import com.spacex.gandalf.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("tidb")
public class TiDBController {

    private Logger logger = LoggerFactory.getLogger(TiDBController.class);

    @Resource
    private UserService userService;

    @RequestMapping(value = "user/getById", method = RequestMethod.GET)
    public UserDTO getById(Long id) {
        logger.info(String.format("TiDBController#getById id:%s", id));
        if (id == null) {
            return null;
        }
        UserDTO userDTO = userService.getById(id);
        return userDTO;
    }
}
