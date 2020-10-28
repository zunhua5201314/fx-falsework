package com.epri.fx.server.rest;


import com.epri.fx.server.entity.Menu;
import com.epri.fx.server.service.MenuService;
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
@RequestMapping("menu")
public class MenuController {
    @Autowired
    private MenuService service;

    @GetMapping("/all")
    @ResponseBody
    public List<MenuVO> getMenuAll() {

        return service.getMenuAll();
    }


    @PostMapping("")
    @ResponseBody
    public Integer addMenu(@RequestBody MenuVO menuVO) {

        return service.addMenu(menuVO);
    }

    @PutMapping("")
    @ResponseBody
    public Integer updateMenu(@RequestBody MenuVO menuVO) {

        return service.updateMenu(menuVO);
    }

    @DeleteMapping("")
    @ResponseBody
    public Integer deleteMenu(@RequestBody MenuVO menuVO) {

        return service.deleteMenu(menuVO);
    }


}
