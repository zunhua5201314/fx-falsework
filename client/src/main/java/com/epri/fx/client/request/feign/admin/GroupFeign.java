package com.epri.fx.client.request.feign.admin;

import com.epri.fx.client.request.feign.FeignAPI;
import com.epri.fx.server.vo.GroupUsers;
import com.epri.fx.server.vo.GroupVO;
import com.epri.fx.server.vo.MenuVO;
import feign.Param;
import feign.QueryMap;
import feign.RequestLine;

import java.util.List;
import java.util.Map;

/**
 * @description:
 * @className: TestFeign
 * @author: liwen
 * @date: 2020/4/1 17:31
 */
public interface GroupFeign extends FeignAPI {
    @RequestLine("GET /group/treeList/{groupTypeId}")
    List<GroupVO> getGroupList(@Param("groupTypeId") Integer groupTypeId);

    @RequestLine("GET /group/{groupId}/authority/menu")
    List<MenuVO> getAuthorityMenuElementAll(@Param("groupId") Integer groupId);

    @RequestLine("PUT /group/{groupId}/authority/menu")
    Integer modifyMenuAuthority(@Param("groupId") int id, List<MenuVO> menuVOList);

    @RequestLine("POST /group")
    Integer addGroup(GroupVO groupVO);

    @RequestLine("PUT /group")
    Integer updateGroup(GroupVO groupVO);

    @RequestLine("DELETE /group")
    Integer deleteGroup(GroupVO groupVO);


    @RequestLine(value = "GET /group/{id}/user")
    public GroupUsers getUsers(@Param("id") int id);

    @RequestLine(value = "PUT /group/{id}/user")
    public Integer modifiyUsers(@Param("id") int id, @QueryMap Map<String,String> map);

}
