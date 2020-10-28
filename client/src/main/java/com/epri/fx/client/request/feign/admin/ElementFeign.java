package com.epri.fx.client.request.feign.admin;

import com.epri.fx.client.request.feign.FeignAPI;
import com.epri.fx.server.entity.Element;
import com.epri.fx.server.msg.TableResultResponse;
import feign.Param;
import feign.RequestLine;

/**
 * @description:
 * @className: ElementFeign
 * @author: liwen
 * @date: 2020/7/19 18:55
 */
public interface ElementFeign extends FeignAPI {

    @RequestLine("GET /element/list/{menuId}")
    TableResultResponse<Element> getMenuElementList(@Param("menuId") Integer menuId);

    @RequestLine("POST /element")
    Integer addElement(Element element);

    @RequestLine("PUT /element")
    Integer updateElement(Element element);

    @RequestLine("DELETE /element/{id}")
    Integer deleteElement(@Param("id") Integer id);

}
