package com.epri.fx.server.mapper;

import com.epri.fx.server.entity.GroupType;

import java.util.List;

public interface GroupTypeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(GroupType record);

    int insertSelective(GroupType record);

    GroupType selectByPrimaryKey(Integer id);

    List<GroupType> selectListAll();

    int updateByPrimaryKeySelective(GroupType record);

    int updateByPrimaryKey(GroupType record);
}