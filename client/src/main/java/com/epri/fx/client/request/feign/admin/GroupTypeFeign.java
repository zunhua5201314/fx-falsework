package com.epri.fx.client.request.feign.admin;

import com.epri.fx.client.request.feign.FeignAPI;
import com.epri.fx.server.vo.GroupTypeVO;
import feign.Param;
import feign.RequestLine;

import java.util.List;

/**
 * @description:
 * @className: TestFeign
 * @author: liwen
 * @date: 2020/4/1 17:31
 */
public interface GroupTypeFeign extends FeignAPI {
    @RequestLine("GET /groupType/all")
    List<GroupTypeVO> getAllGroupTypes();

    @RequestLine("POST /groupType")
    Integer addGroupType(GroupTypeVO groupTypeVO);

    @RequestLine("PUT /groupType")
    Integer updateGroupType(GroupTypeVO groupTypeVO);

    @RequestLine("DELETE /groupType/{groupTypeId}")
    Integer deleteGroupTypes(@Param("groupTypeId") int groupTypeId);

}
