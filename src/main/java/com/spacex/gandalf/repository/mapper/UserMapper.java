package com.spacex.gandalf.repository.mapper;


import com.spacex.gandalf.repository.po.UserPO;
import tk.mybatis.mapper.common.Mapper;

@org.apache.ibatis.annotations.Mapper
public interface UserMapper extends Mapper<UserPO> {
}
