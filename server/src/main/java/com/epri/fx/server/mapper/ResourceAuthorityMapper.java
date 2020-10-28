package com.epri.fx.server.mapper;

import com.epri.fx.server.entity.ResourceAuthority;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ResourceAuthorityMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ResourceAuthority record);

    int insertSelective(ResourceAuthority record);

    ResourceAuthority selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ResourceAuthority record);

    int updateByPrimaryKey(ResourceAuthority record);

    List<ResourceAuthority> select(ResourceAuthority record);

    int deleteByAuthorityIdAndResourceType(@Param("authorityId")String authorityId);
    int deleteByresourceIdAndResourceType(@Param("resourceId")String resourceId,@Param("resourceType")String resourceType);
    int deleteByAuthorityIdAndResourceId(@Param("authorityId")String authorityId,@Param("resourceId") String resourceId);

}