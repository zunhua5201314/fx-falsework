package com.epri.fx.server.mapper;

import com.epri.fx.server.entity.Group;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GroupMapper {
    int deleteByPrimaryKey(Integer id);

    int deleteChilder(Integer id);

    int insert(Group record);

    int insertSelective(Group record);

    Group selectByPrimaryKey(Integer id);

    List<Group> selectGroupList(Integer groupType);

    int updateByPrimaryKeySelective(Group record);

    int updateByPrimaryKey(Group record);

    int deleteGroupMembersById(@Param("groupId") int groupId);

    int  deleteGroupLeadersById(@Param("groupId") int groupId);

    int insertGroupMembersById(@Param("groupId") int groupId, @Param("userId") int userId);

    int insertGroupLeadersById(@Param("groupId") int groupId, @Param("userId") int userId);

    List<Group> selectUserGroupList(Integer userId);

}