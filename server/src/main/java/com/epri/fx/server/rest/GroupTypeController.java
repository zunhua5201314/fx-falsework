package com.epri.fx.server.rest;


import com.epri.fx.server.service.GroupTypeService;
import com.epri.fx.server.vo.GroupTypeVO;
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
@RestController
@RequestMapping("groupType")
public class GroupTypeController {
    @Autowired
    private GroupTypeService groupTypeService;

    @GetMapping(value = "/all")
    @ResponseBody
    public List<GroupTypeVO> getAllGroupTypes() {

        return groupTypeService.getAllGroupTypes();
    }

    @PostMapping(value = "")
    @ResponseBody
    public Integer addGroupType(@RequestBody GroupTypeVO groupTypeVO) {

        return groupTypeService.addGroupType(groupTypeVO);
    }

    @PutMapping(value = "")
    @ResponseBody
    public Integer updateGroupType(@RequestBody GroupTypeVO groupTypeVO) {

        return groupTypeService.updateGroupType(groupTypeVO);
    }

    @DeleteMapping(value = "/{groupTypeId}")
    @ResponseBody
    public Integer deleteGroupType(@PathVariable int groupTypeId) {

        return groupTypeService.deleteGroupType(groupTypeId);
    }


}
