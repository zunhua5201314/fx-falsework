package com.epri.fx.server.service;

import com.epri.fx.server.entity.RsaKey;
import com.epri.fx.server.mapper.RsaKeyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @className: RsaKeyService
 * @author: liwen
 * @date: 2020/8/2 11:12
 */
@Service
public class RsaKeyService {

    @Autowired
    private RsaKeyMapper rsaKeyMapper;

    public boolean hasKey(String key) {
        return rsaKeyMapper.selectByPrimaryKey(key) != null;
    }

    public String get(String key) {
        RsaKey rsaKey = rsaKeyMapper.selectByPrimaryKey(key);
        return rsaKey == null ? "" : rsaKey.getValue();
    }

    public int set(String key, String value) {
        RsaKey rsaKey = new RsaKey();
        rsaKey.setKey(key);
        rsaKey.setValue(value);
        return rsaKeyMapper.insert(rsaKey);
    }
}
