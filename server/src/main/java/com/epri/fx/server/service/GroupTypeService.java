package com.epri.fx.server.service;

import com.epri.fx.server.entity.GroupType;
import com.epri.fx.server.mapper.GroupTypeMapper;
import com.epri.fx.server.util.EntityUtils;
import com.epri.fx.server.vo.GroupTypeVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @className: GroupTypeService
 * @author: liwen
 * @date: 2020/6/30 21:58
 */
@Service
public class GroupTypeService {

    @Autowired
    private GroupTypeMapper groupTypeMapper;

    public List<GroupTypeVO> getAllGroupTypes() {
        List<GroupType> groupTypeList = groupTypeMapper.selectListAll();
        List<GroupTypeVO> groupTypeVOList = new ArrayList<>();
        for (GroupType type : groupTypeList) {
            GroupTypeVO groupTypeVO = new GroupTypeVO();
            BeanUtils.copyProperties(type, groupTypeVO);
            groupTypeVOList.add(groupTypeVO);
        }
        return groupTypeVOList;
    }

    public Integer addGroupType(GroupTypeVO groupTypeVO) {
        GroupType groupType = new GroupType();
        BeanUtils.copyProperties(groupTypeVO, groupType);
        EntityUtils.setCreateInfo(groupType);
        groupType.setId(null);
       return groupTypeMapper.insertSelective(groupType);
    }

    public Integer updateGroupType(GroupTypeVO groupTypeVO) {
        GroupType groupType = new GroupType();
        BeanUtils.copyProperties(groupTypeVO, groupType);
        EntityUtils.setUpdatedInfo(groupType);
        return groupTypeMapper.updateByPrimaryKeySelective(groupType);
    }

    public Integer deleteGroupType(int groupTypeId) {
        return groupTypeMapper.deleteByPrimaryKey(groupTypeId);
    }


}
