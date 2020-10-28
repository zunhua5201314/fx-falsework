package com.epri.fx.client.request.feign.admin;

import com.epri.fx.client.request.feign.FeignAPI;
import com.epri.fx.server.entity.User;
import com.epri.fx.server.msg.ObjectRestResponse;
import com.epri.fx.server.msg.TableResultResponse;
import com.epri.fx.server.vo.UserVO;
import feign.Param;
import feign.QueryMap;
import feign.RequestLine;

import java.util.Map;

/**
 * @description:
 * @className: TestFeign
 * @author: liwen
 * @date: 2020/4/1 17:31
 */
public interface UserFeign extends FeignAPI {
    @RequestLine("GET /user/page")
    TableResultResponse<User> getPageList(@QueryMap Map<String, Object> map);

    @RequestLine("PUT /user/{id}")
    ObjectRestResponse<Integer> update(@Param("id") Integer id, User user);

    @RequestLine("DELETE /user/{id}")
    ObjectRestResponse<Integer> delete(@Param("id") Integer id);

    @RequestLine("PUT /user/password/{id}")
    ObjectRestResponse<Integer> restPassword(@Param("id")Integer id);

    @RequestLine("POST /user")
    ObjectRestResponse<Integer> add(User user);
}
