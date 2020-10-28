package com.epri.fx.server.rest;


import com.epri.fx.server.service.GroupService;
import com.epri.fx.server.vo.GroupUsers;
import com.epri.fx.server.vo.GroupVO;
import com.epri.fx.server.vo.MenuVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * ${DESCRIPTION}
 *
 * @author wanghaobin
 * @create 2017-06-12 8:49
 */
@Controller
@RequestMapping("group")
public class GroupController {
    @Autowired
    private GroupService groupService;


    @GetMapping("/{groupId}/authority/menu")
    @ResponseBody
    public List<MenuVO> getAuthorityMenuElementAll(@PathVariable Integer groupId) {

        return groupService.getAuthorityMenuElementAll(groupId);
    }

    @GetMapping(value = "/treeList/{groupTypeId}")
    @ResponseBody
    public List<GroupVO> getGroupList(@PathVariable Integer groupTypeId) {

        return groupService.getGroupList(groupTypeId);
    }

    @PostMapping(value = "")
    @ResponseBody
    public Integer addGroup(@RequestBody GroupVO groupVO) {

        return groupService.addGroup(groupVO);
    }


    @PutMapping(value = "")
    @ResponseBody
    public Integer updateGroup(@RequestBody GroupVO groupVO) {

        return groupService.updateGroup(groupVO);
    }


    @DeleteMapping(value = "")
    @ResponseBody
    public Integer deleteGroup(@RequestBody GroupVO groupVO) {

        return groupService.deleteGroup(groupVO);
    }


    @RequestMapping(value = "/{id}/authority/element", method = RequestMethod.GET)
    @ResponseBody
    public List<Integer> getElementAuthority(@PathVariable int id) {
        return groupService.getAuthorityElement(id);
    }

    @PutMapping(value = "/{groupId}/authority/menu")
    @ResponseBody
    public Integer modifyMenuAuthority(@PathVariable int groupId, @RequestBody List<MenuVO> menuVOList) {
        return groupService.modifyAuthorityMenu(groupId, menuVOList);
    }


    @RequestMapping(value = "/{id}/user", method = RequestMethod.PUT)
    @ResponseBody
    public Integer modifiyUsers(@PathVariable int id, String members, String leaders) {

        return groupService.modifyGroupUsers(id, members, leaders);
    }

    @RequestMapping(value = "/{id}/user", method = RequestMethod.GET)
    @ResponseBody
    public GroupUsers getUsers(@PathVariable int id) {
        return groupService.getGroupUsers(id);
    }


}
