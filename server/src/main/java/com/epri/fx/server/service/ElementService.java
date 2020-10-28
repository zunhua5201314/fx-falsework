package com.epri.fx.server.service;

import com.epri.fx.server.entity.Element;
import com.epri.fx.server.mapper.ElementMapper;
import com.epri.fx.server.msg.TableResultResponse;
import com.epri.fx.server.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description:
 * @className: ElementService
 * @author: liwen
 * @date: 2020/7/19 12:44
 */
@Service
public class ElementService {

    @Autowired
    private ElementMapper elementMapper;

    public TableResultResponse<Element> getElement(Integer menuId) {
        List<Element> elementList = elementMapper.selectElementList(menuId);

        return new TableResultResponse<Element>(elementList.size(), elementList);
    }

    public Integer addElement(Element element) {

        EntityUtils.setCreatAndUpdatInfo(element);
        return elementMapper.insertSelective(element);
    }

    public Integer updateElement(Element element) {
        EntityUtils.setCreatAndUpdatInfo(element);
        return elementMapper.updateByPrimaryKeySelective(element);
    }

    public Integer deleteElement(Integer id) {
        return elementMapper.deleteByPrimaryKey(id);
    }

    public List<Element> getAllElementPermissions(){
        return elementMapper.selectAllElementPermissions();
    }
    public List<Element> getAuthorityElementByUserId(String userId){
        return elementMapper.selectAuthorityElementByUserId(userId);
    }
}
