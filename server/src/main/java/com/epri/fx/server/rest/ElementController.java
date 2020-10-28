package com.epri.fx.server.rest;

import com.epri.fx.server.entity.Element;
import com.epri.fx.server.msg.TableResultResponse;
import com.epri.fx.server.service.ElementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @description:
 * @className: ElementController
 * @author: liwen
 * @date: 2020/7/19 12:44
 */
@RestController
@RequestMapping("element")
public class ElementController {
    @Autowired
    private ElementService elementService;

    @GetMapping(value = "/list/{menuId}")
    @ResponseBody
    public TableResultResponse<Element> getElement(@PathVariable Integer menuId) {

        return elementService.getElement(menuId);
    }

    @PostMapping(value = "")
    @ResponseBody
    public Integer addElement(@RequestBody Element element) {

        return elementService.addElement(element);
    }

    @PutMapping(value = "")
    @ResponseBody
    public Integer updateElement(@RequestBody Element element) {

        return elementService.updateElement(element);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseBody
    public Integer deleteElement(@PathVariable Integer id) {

        return elementService.deleteElement(id);
    }

}
