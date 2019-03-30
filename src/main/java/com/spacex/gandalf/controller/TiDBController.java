package com.spacex.gandalf.controller;

import com.spacex.gandalf.dto.SimpleBooleanResultDTO;
import com.spacex.gandalf.dto.UserDTO;
import com.spacex.gandalf.dto.pagination.PaginationDTO;
import com.spacex.gandalf.service.UserService;
import com.spacex.gandalf.util.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("tidb")
public class TiDBController {

    private Logger logger = LoggerFactory.getLogger(TiDBController.class);

    @Resource
    private UserService userService;

    @RequestMapping(value = "users", method = RequestMethod.POST)
    public List<UserDTO> getByPagination(@RequestBody PaginationDTO paginationDTO) {
        logger.info(String.format("TiDBController#getByPagination paginationDTO:%s", JsonUtil.toJson(paginationDTO)));
        List<UserDTO> userDTOs = userService.getByPagination(paginationDTO);
        return userDTOs;
    }

    @RequestMapping(value = "user/{id}", method = RequestMethod.GET)
    public UserDTO getById(@PathVariable Long id) {
        logger.info(String.format("TiDBController#getById id:%s", id));
        if (id == null) {
            return null;
        }
        UserDTO userDTO = userService.getById(id);
        return userDTO;
    }

    @RequestMapping(value = "user", method = RequestMethod.POST)
    public UserDTO create(UserDTO userDTO) {
        logger.info(String.format("TiDBController#create userDTO:%s", JsonUtil.toJson(userDTO)));
        UserDTO result = userService.create(userDTO);
        return result;
    }

    @RequestMapping(value = "user", method = RequestMethod.PUT)
    public UserDTO update(@RequestBody UserDTO userDTO) {
        logger.info(String.format("TiDBController#update userDTO:%s", JsonUtil.toJson(userDTO)));
        UserDTO result = userService.update(userDTO);
        return result;
    }

    @RequestMapping(value = "user", method = RequestMethod.DELETE)
    public SimpleBooleanResultDTO delete(Long userId) {
        logger.info(String.format("TiDBController#delete userId:%s", JsonUtil.toJson(userId)));
        Boolean result = userService.delete(userId);
        return new SimpleBooleanResultDTO(result);
    }
}
