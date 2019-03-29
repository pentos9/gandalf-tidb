package com.spacex.gandalf.service;

import com.spacex.gandalf.dto.UserDTO;
import com.spacex.gandalf.repository.mapper.UserMapper;
import com.spacex.gandalf.repository.po.UserPO;
import com.spacex.gandalf.util.BeanCopyUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserService {

    private Logger logger = LoggerFactory.getLogger(UserService.class);

    @Resource
    private UserMapper userMapper;

    public UserDTO getById(Long id) {
        logger.info(String.format("UserService#getById id:%s", id));

        Example example = new Example(UserPO.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("id", id);
        List<UserPO> userPOs = userMapper.selectByExample(example);

        if (userPOs == null || userPOs.size() <= 0) {
            return null;
        }

        UserPO userPO = userPOs.get(0);
        UserDTO userDTO = BeanCopyUtil.map(userPO, UserDTO.class);
        return userDTO;
    }
}
