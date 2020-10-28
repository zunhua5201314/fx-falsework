package com.epri.fx.server.mapper;

import com.epri.fx.server.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);
    User selectOne(String username );

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    List<User> selectAll();
    List<User> selectPage(String keyId);

    public List<User> selectMemberByGroupId(@Param("groupId") int groupId);
    public List<User> selectLeaderByGroupId(@Param("groupId") int groupId);


}