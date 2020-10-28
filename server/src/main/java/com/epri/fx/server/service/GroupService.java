package com.epri.fx.server.service;

import com.epri.fx.server.constant.AdminCommonConstant;
import com.epri.fx.server.entity.Element;
import com.epri.fx.server.entity.Group;
import com.epri.fx.server.entity.Menu;
import com.epri.fx.server.entity.ResourceAuthority;
import com.epri.fx.server.mapper.*;
import com.epri.fx.server.util.EntityUtils;
import com.epri.fx.server.vo.ElementVO;
import com.epri.fx.server.vo.GroupUsers;
import com.epri.fx.server.vo.GroupVO;
import com.epri.fx.server.vo.MenuVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @description:
 * @className: GroupTypeService
 * @author: liwen
 * @date: 2020/6/30 21:58
 */
@Service
public class GroupService {

    @Autowired
    private GroupMapper groupMapper;
    @Autowired
    private MenuMapper menuMapper;
    @Autowired
    private ResourceAuthorityMapper resourceAuthorityMapper;
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ElementMapper elementMapper;

    public List<GroupVO> getGroupList(Integer groupTypeId) {
        List<Group> groupList = groupMapper.selectGroupList(groupTypeId);
        List<GroupVO> groupVOList = new ArrayList<>();
        for (Group group : groupList) {
            GroupVO groupVO = new GroupVO();
            BeanUtils.copyProperties(group, groupVO);
            groupVOList.add(groupVO);
        }
        return groupVOList;
    }

    public Integer addGroup(GroupVO groupVO) {

        Group group = new Group();
        BeanUtils.copyProperties(groupVO, group);
        group.setId(null);
        EntityUtils.setCreatAndUpdatInfo(group);
        return groupMapper.insertSelective(group);
    }

    public Integer updateGroup(GroupVO groupVO) {

        Group group = new Group();
        BeanUtils.copyProperties(groupVO, group);
        EntityUtils.setCreatAndUpdatInfo(group);
        return groupMapper.updateByPrimaryKeySelective(group);
    }

    public Integer deleteGroup(GroupVO groupVO) {

        int result = -1;
        result = groupMapper.deleteByPrimaryKey(groupVO.getId());
        result = groupMapper.deleteChilder(groupVO.getId());
        return result;
    }


    public List<MenuVO> getAuthorityMenuElementAll(Integer groupId) {

        List<Menu> menus = menuMapper.selectMenuAll();

        List<MenuVO> menuVOList = new ArrayList<>();
        Map<Integer, Menu> authorityMenuMap = getAuthorityMenu(groupId).stream().collect(Collectors.toMap(Menu::getId, a -> a, (k1, k2) -> k1));
        List<Integer> authorityElementList = getAuthorityElement(groupId);
        for (Menu menu : menus) {
            boolean sel = authorityMenuMap.get(menu.getId()) != null;
            List<Element> elements = elementMapper.selectElementList(menu.getId());
            menu.setElementList(elements);

            MenuVO menuVO = new MenuVO();
            BeanUtils.copyProperties(menu, menuVO);
            List<ElementVO> elementVOList = new ArrayList<>();
            for (Element element : elements) {
                ElementVO elementVO = new ElementVO();
                BeanUtils.copyProperties(element, elementVO);
                elementVO.setSel(authorityElementList.contains(element.getId()));
                elementVOList.add(elementVO);
            }
            menuVO.setSel(sel);
            menuVOList.add(menuVO);
            menuVO.getElementVOS().addAll(elementVOList);
        }

        return menuVOList;
    }

    /**
     * 获取群主关联的菜单
     *
     * @param groupId
     * @return
     */
    public List<Menu> getAuthorityMenu(int groupId) {
        List<Menu> menus = menuMapper.selectMenuByAuthorityId(String.valueOf(groupId), AdminCommonConstant.AUTHORITY_TYPE_GROUP);
        return menus;
    }

    /**
     * 获取群组关联的资源
     *
     * @param groupId
     * @return
     */
    public List<Integer> getAuthorityElement(int groupId) {
        ResourceAuthority authority = new ResourceAuthority(AdminCommonConstant.AUTHORITY_TYPE_GROUP, AdminCommonConstant.RESOURCE_TYPE_BTN);
        authority.setAuthorityId(groupId + "");
        List<ResourceAuthority> authorities = resourceAuthorityMapper.select(authority);
        List<Integer> ids = new ArrayList<Integer>();
        for (ResourceAuthority auth : authorities) {
            ids.add(Integer.parseInt(auth.getResourceId()));
        }
        return ids;
    }


    /**
     * 变更群组关联的菜单
     *
     * @param groupId
     * @param menus
     */
    public Integer modifyAuthorityMenu(int groupId, List<MenuVO> menus) {
        int result = -1;
        resourceAuthorityMapper.deleteByAuthorityIdAndResourceType(groupId + "");
        ResourceAuthority authority = null;
        for (MenuVO menu : menus) {

            for (ElementVO element : menu.getElementVOS()) {
                authority = new ResourceAuthority(AdminCommonConstant.AUTHORITY_TYPE_GROUP, AdminCommonConstant.RESOURCE_TYPE_BTN);
                authority.setAuthorityId(groupId + "");
                authority.setResourceId(element.getId() + "");
                authority.setParentId("-1");
                resourceAuthorityMapper.insertSelective(authority);
            }
            authority = new ResourceAuthority(AdminCommonConstant.AUTHORITY_TYPE_GROUP, AdminCommonConstant.RESOURCE_TYPE_MENU);
            authority.setAuthorityId(groupId + "");
            authority.setResourceId(menu.getId() + "");
            authority.setParentId("-1");
            result = resourceAuthorityMapper.insertSelective(authority);
        }
        return result;
    }


    /**
     * 获取群组关联用户
     *
     * @param groupId
     * @return
     */
    public GroupUsers getGroupUsers(int groupId) {

        return new GroupUsers(userMapper.selectMemberByGroupId(groupId), userMapper.selectLeaderByGroupId(groupId), userMapper.selectAll());
    }


    /**
     * 获取用户关联群组
     *
     * @param userId
     * @return
     */
    public List<GroupVO> getUserGroups(int userId) {
        List<Group> groups = groupMapper.selectUserGroupList(userId);
        List<GroupVO> groupVOList = new ArrayList<>();
        for (Group group : groups) {
            GroupVO groupVO = new GroupVO();
            BeanUtils.copyProperties(group, groupVO);
            groupVOList.add(groupVO);
        }
        return groupVOList;
    }

    /**
     * 变更群主所分配用户
     *
     * @param groupId
     * @param members
     * @param leaders
     */
    public Integer modifyGroupUsers(int groupId, String members, String leaders) {
        int result = -1;
        result = groupMapper.deleteGroupLeadersById(groupId);
        result = groupMapper.deleteGroupMembersById(groupId);
        if (!StringUtils.isEmpty(members)) {
            members = members.replace("[", "").replace("]", "");
            String[] mem = members.split(",");
            for (String m : mem) {
                result = groupMapper.insertGroupMembersById(groupId, Integer.parseInt(m.trim()));
            }
        }
        if (!StringUtils.isEmpty(leaders)) {
            leaders = leaders.replace("[", "").replace("]", "");
            String[] mem = leaders.split(",");
            for (String m : mem) {
                result = groupMapper.insertGroupLeadersById(groupId, Integer.parseInt(m));
            }
        }

        return result;
    }

}
