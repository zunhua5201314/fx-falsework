package com.epri.fx.client.request.feign.admin;

import com.epri.fx.client.request.feign.FeignAPI;
import com.epri.fx.server.vo.MenuVO;
import feign.Headers;
import feign.RequestLine;

import java.util.List;

/**
 * @description:
 * @className: TestFeign
 * @author: liwen
 * @date: 2020/4/1 17:31
 */
@Headers("Content-Type: application/json")
public interface MenuFeign extends FeignAPI {
    @RequestLine("GET /menu/all")
    List<MenuVO> getMenuAll();
    @RequestLine("GET /menu/element/all")
    List<MenuVO> getMenuElementAll();

    @RequestLine("POST /menu")
    Integer addMenu(MenuVO menuVO);

    @RequestLine("PUT /menu")
    Integer updateMenu(MenuVO menuVO);

    @RequestLine("DELETE /menu")
    Integer deleteMenu(MenuVO menuVO);

}
