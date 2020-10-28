package com.epri.fx.server.mapper;

import com.epri.fx.server.entity.RsaKey;

public interface RsaKeyMapper {
    int deleteByPrimaryKey(String key);

    int insert(RsaKey record);

    int insertSelective(RsaKey record);

    RsaKey selectByPrimaryKey(String key);

    int updateByPrimaryKeySelective(RsaKey record);

    int updateByPrimaryKey(RsaKey record);
}