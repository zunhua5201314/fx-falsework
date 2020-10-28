package com.epri.fx.server.service;

import com.epri.fx.server.constant.AdminCommonConstant;
import com.epri.fx.server.entity.Element;
import com.epri.fx.server.entity.Menu;
import com.epri.fx.server.entity.ResourceAuthority;
import com.epri.fx.server.mapper.ElementMapper;
import com.epri.fx.server.mapper.MenuMapper;
import com.epri.fx.server.mapper.ResourceAuthorityMapper;
import com.epri.fx.server.util.EntityUtils;
import com.epri.fx.server.vo.ElementVO;
import com.epri.fx.server.vo.GroupVO;
import com.epri.fx.server.vo.MenuVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @description:
 * @className: MenuServer
 * @author: liwen
 * @date: 2020/6/30 21:58
 */
@Service
public class MenuService {

    @Autowired
    private MenuMapper menuMapper;
    @Autowired
    private ElementMapper elementMapper;
    @Autowired
    private ResourceAuthorityMapper resourceAuthorityMapper;


    public List<MenuVO> getMenuAll() {

        List<Menu> menus = menuMapper.selectMenuAll();
        List<MenuVO> menuVOList = new ArrayList<>();

        for (Menu menu : menus) {
            MenuVO menuVO = new MenuVO();
            BeanUtils.copyProperties(menu, menuVO);
            menuVOList.add(menuVO);
        }

        return menuVOList;
    }

    public List<Menu> selectListAll() {
        return menuMapper.selectMenuAll();
    }

    public Integer addMenu(MenuVO menuVO) {

        Menu menu = new Menu();
        BeanUtils.copyProperties(menuVO, menu);
        menu.setId(null);
        EntityUtils.setCreatAndUpdatInfo(menu);

        return menuMapper.insertSelective(menu);
    }

    public Integer updateMenu(MenuVO menuVO) {

        Menu menu = new Menu();
        BeanUtils.copyProperties(menuVO, menu);
        EntityUtils.setCreatAndUpdatInfo(menu);

        return menuMapper.updateByPrimaryKeySelective(menu);
    }

    public Integer deleteMenu(MenuVO menuVO) {
        int result = menuMapper.deleteByPrimaryKey(menuVO.getId());
        result = menuMapper.deleteAllChild(menuVO.getId());
        result = resourceAuthorityMapper.deleteByresourceIdAndResourceType(menuVO.getId() + "",AdminCommonConstant.RESOURCE_TYPE_MENU);
        result = elementMapper.deleteByMenuId(menuVO.getId() );
        List<ElementVO> list = menuVO.getElementVOS();
        if (list != null) {
            for (ElementVO element : menuVO.getElementVOS()) {
                result = resourceAuthorityMapper.deleteByresourceIdAndResourceType(element.getId() + "",AdminCommonConstant.RESOURCE_TYPE_BTN);
            }
        }

        return result;
    }


    /**
     * 获取用户可以访问的菜单
     *
     * @param id
     * @return
     */
    public List<Menu> getUserAuthorityMenuByUserId(int id) {
        return menuMapper.selectAuthorityMenuByUserId(id);
    }


}
