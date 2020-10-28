package com.epri.fx.server.mapper;


import com.epri.fx.server.entity.Menu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MenuMapper {
    int deleteByPrimaryKey(Integer id);

    int deleteAllChild(Integer parentId);

    int insert(Menu record);

    int insertSelective(Menu record);

    Menu selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Menu record);

    int updateByPrimaryKey(Menu record);

    List<Menu> selectMenuAll();

    List<Menu> selectMenuByAuthorityId(@Param("authorityId") String authorityId,@Param("authorityType") String authorityType);
    List<Menu> selectAuthorityMenuByUserId (@Param("userId") int userId);
  
}