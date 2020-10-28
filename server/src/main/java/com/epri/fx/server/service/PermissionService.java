package com.epri.fx.server.service;

import com.epri.fx.server.constant.AdminCommonConstant;
import com.epri.fx.server.constant.CommonConstants;
import com.epri.fx.server.entity.Element;
import com.epri.fx.server.entity.Menu;
import com.epri.fx.server.entity.User;
import com.epri.fx.server.entity.UserInfo;
import com.epri.fx.server.jwt.UserAuthUtil;
import com.epri.fx.server.util.EncryptUtil;
import com.epri.fx.server.vo.FrontUser;
import com.epri.fx.server.vo.GroupVO;
import com.epri.fx.server.vo.MenuVO;
import com.epri.fx.server.vo.PermissionInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Description:
 * @param:
 * @return:
 * @auther: liwen
 * @date: 2020/8/2 10:56 上午
 */
@Service
public class PermissionService {
    @Autowired
    private UserService userService;
    @Autowired
    private MenuService menuService;
    @Autowired
    private ElementService elementService;
    @Autowired
    private UserAuthUtil userAuthUtil;
    @Autowired
    private GroupService groupService;
    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);


    public UserInfo getUserByUsername(String username) {
        UserInfo info = new UserInfo();
        User user = userService.getUserByUsername(username);
        BeanUtils.copyProperties(user, info);
        info.setId(user.getId().toString());
        return info;
    }

    public UserInfo validate(String username, String password) {
        UserInfo info = new UserInfo();
        User user = userService.getUserByUsername(username);
        String pwd = "";
        try {
            pwd = EncryptUtil.getInstance().Base64Decode(password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (user != null && encoder.matches(pwd, user.getPassword())) {
            BeanUtils.copyProperties(user, info);
            info.setId(user.getId().toString());
        }
        return info;
    }

    public List<PermissionInfo> getAllPermission() {
        List<Menu> menus = menuService.selectListAll();
        List<PermissionInfo> result = new ArrayList<PermissionInfo>();
        PermissionInfo info = null;
        menu2permission(menus, result);
        List<Element> elements = elementService.getAllElementPermissions();
        element2permission(result, elements);
        return result;
    }

    private void menu2permission(List<Menu> menus, List<PermissionInfo> result) {
        PermissionInfo info;
        for (Menu menu : menus) {
            if (StringUtils.isBlank(menu.getHref())) {
                menu.setHref("/" + menu.getCode());
            }
            info = new PermissionInfo();
            info.setCode(menu.getCode());
            info.setType(AdminCommonConstant.RESOURCE_TYPE_MENU);
            info.setName(AdminCommonConstant.RESOURCE_ACTION_VISIT);
            String uri = menu.getHref();
            if (!uri.startsWith("/")) {
                uri = "/" + uri;
            }
            info.setUri(uri);
            info.setMethod(AdminCommonConstant.RESOURCE_REQUEST_METHOD_GET);
            result.add(info
            );
            info.setMenu(menu.getTitle());
        }
    }

    public List<PermissionInfo> getPermissionByUsername(String username) {
        User user = userService.getUserByUsername(username);
        List<Menu> menus = menuService.getUserAuthorityMenuByUserId(user.getId());
        List<PermissionInfo> result = new ArrayList<PermissionInfo>();
        PermissionInfo info = null;
        menu2permission(menus, result);
        List<Element> elements = elementService.getAuthorityElementByUserId(user.getId() + "");
        element2permission(result, elements);
        return result;
    }

    private void element2permission(List<PermissionInfo> result, List<Element> elements) {
        PermissionInfo info;
        for (Element element : elements) {
            info = new PermissionInfo();
            info.setCode(element.getCode());
            info.setType(element.getType());
            info.setUri(element.getUri());
            info.setMethod(element.getMethod());
            info.setName(element.getName());
            info.setMenu(element.getMenuId());
            result.add(info);
        }
    }


    public FrontUser getUserInfo(String token) throws Exception {
        String username = userAuthUtil.getInfoFromToken(token).getUniqueName();
        if (username == null) {
            return null;
        }
        UserInfo user = this.getUserByUsername(username);
        FrontUser frontUser = new FrontUser();
        BeanUtils.copyProperties(user, frontUser);
        List<PermissionInfo> permissionInfos = this.getPermissionByUsername(username);
        Stream<PermissionInfo> menus = permissionInfos.parallelStream().filter((permission) -> {
            return permission.getType().equals(CommonConstants.RESOURCE_TYPE_MENU);
        });
        frontUser.setMenus(menus.collect(Collectors.toList()));
        Stream<PermissionInfo> elements = permissionInfos.parallelStream().filter((permission) -> {
            return !permission.getType().equals(CommonConstants.RESOURCE_TYPE_MENU);
        });
        frontUser.setElements(elements.collect(Collectors.toList()));

        List<GroupVO> groupVOList= groupService.getUserGroups(Integer.parseInt(user.getId()));

        frontUser.setRoles(groupVOList);

        return frontUser;
    }


    public List<MenuVO> getMenusByUsername(String token) throws Exception {
        String username = userAuthUtil.getInfoFromToken(token).getUniqueName();
        if (username == null) {
            return null;
        }
        User user = userService.getUserByUsername(username);
        List<Menu> menus = menuService.getUserAuthorityMenuByUserId(user.getId());
        List<MenuVO> menuVOS = new ArrayList<>();
        for (Menu menu : menus) {
            MenuVO menuVO = new MenuVO();
            BeanUtils.copyProperties(menu, menuVO);
            menuVOS.add(menuVO);
        }
        return menuVOS;
    }


}
