package com.spacex.gandalf.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.google.common.collect.Lists;
import com.spacex.gandalf.dto.UserDTO;
import com.spacex.gandalf.dto.pagination.PaginationDTO;
import com.spacex.gandalf.enums.DeleteStatusEnum;
import com.spacex.gandalf.repository.mapper.UserMapper;
import com.spacex.gandalf.repository.po.UserPO;
import com.spacex.gandalf.util.BeanCopyUtil;
import com.spacex.gandalf.util.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class UserService {

    private Logger logger = LoggerFactory.getLogger(UserService.class);

    @Resource
    private UserMapper userMapper;

    public List<UserDTO> getByPagination(PaginationDTO paginationDTO) {
        logger.info(String.format("UserService#getByPagination paginationDTO:%s", JsonUtil.toJson(paginationDTO)));
        Integer pageNum = paginationDTO.getPageNum();
        Integer size = paginationDTO.getSize();
        Page<UserDTO> page = PageHelper.startPage(pageNum, size);
        Example example = new Example(UserPO.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("deleted", DeleteStatusEnum.NOT_DELETED.getCode());

        List<UserPO> userPOs = userMapper.selectByExample(example);

        if (userPOs == null || userPOs.isEmpty()) {
            return Lists.newArrayList();
        }

        List<UserDTO> userDTOs = BeanCopyUtil.mapList(userPOs, UserDTO.class);
        return userDTOs;
    }

    public UserDTO getById(Long id) {
        logger.info(String.format("UserService#getById id:%s", id));

        Example example = new Example(UserPO.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("id", id);
        criteria.andEqualTo("deleted", DeleteStatusEnum.NOT_DELETED.getCode());
        List<UserPO> userPOs = userMapper.selectByExample(example);

        if (userPOs == null || userPOs.size() <= 0) {
            return null;
        }

        UserPO userPO = userPOs.get(0);
        UserDTO userDTO = BeanCopyUtil.map(userPO, UserDTO.class);
        return userDTO;
    }

    @Transactional
    public UserDTO create(UserDTO userDTO) {
        if (userDTO == null) {
            return null;
        }

        UserPO userPO = BeanCopyUtil.map(userDTO, UserPO.class);
        userPO.setCreatedTime(new Date());
        int row = userMapper.insertSelective(userPO);
        Long userId = userPO.getId();
        return getById(userId);
    }

    @Transactional
    public UserDTO update(UserDTO userDTO) {
        if (userDTO == null) {
            return null;
        }

        Long userId = userDTO.getId();
        Example example = new Example(UserPO.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("id", userId);
        UserPO userPO = BeanCopyUtil.map(userDTO, UserPO.class);
        userPO.setModifiedTime(new Date());
        userMapper.updateByExampleSelective(userPO, example);
        return getById(userId);
    }

    @Transactional
    public boolean delete(Long userId) {
        if (userId == null) {
            return false;
        }

        Example example = new Example(UserPO.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("id", userId);

        UserPO userPO = new UserPO();
        userPO.setDeleted(DeleteStatusEnum.DELETED.getCode());
        Date current = new Date();
        userPO.setModifiedTime(current);
        userPO.setDeletedTime(current);
        int effectedRow = userMapper.updateByExample(userPO, example);
        return effectedRow > 0;
    }


}
