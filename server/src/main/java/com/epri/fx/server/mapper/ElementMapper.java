package com.epri.fx.server.mapper;

import com.epri.fx.server.entity.Element;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ElementMapper {
    int deleteByPrimaryKey(Integer id);
    int deleteByMenuId(Integer menuId);

    int insert(Element record);

    int insertSelective(Element record);

    Element selectByPrimaryKey(Integer id);

    List<Element> selectElementList(Integer menuId);

    int updateByPrimaryKeySelective(Element record);

    int updateByPrimaryKey(Element record);

    List<Element> selectAllElementPermissions();

    List<Element> selectAuthorityElementByUserId(@Param("userId") String userId);

}