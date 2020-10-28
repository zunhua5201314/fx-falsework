package com.epri.fx.client.store;

import com.epri.fx.client.bean.MenuVoCell;
import com.epri.fx.server.vo.GroupVO;
import com.epri.fx.server.vo.MenuVO;
import com.epri.fx.server.vo.PermissionInfo;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;

import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @className: User
 * @author: liwen
 * @date: 2020/8/2 10:17
 */

public class ApplicatonStore {

    public static String ICON_FONT_KEY = "icon.svg";
    private static SimpleIntegerProperty status = new SimpleIntegerProperty();
    private static SimpleStringProperty code = new SimpleStringProperty();
    private static SimpleStringProperty token = new SimpleStringProperty();
    private static SimpleStringProperty name = new SimpleStringProperty();
    private static SimpleStringProperty avatar = new SimpleStringProperty();
    private static SimpleStringProperty introduction = new SimpleStringProperty();
    private static ListProperty<GroupVO> roles;
    private static ListProperty<PermissionInfo> menus;
    private static ListProperty<MenuVO> permissionMenus;
    private static ListProperty<MenuVO> allMenu;
    private static ListProperty<MenuVoCell> menuVoCells;
    private static ListProperty<PermissionInfo> elements;
    private static MapProperty<String, String> featureMap;


    public static int getStatus() {
        return status.get();
    }

    public static SimpleIntegerProperty statusProperty() {
        return status;
    }

    public static void setStatus(int status) {
        ApplicatonStore.status.set(status);
    }

    public static String getCode() {
        return code.get();
    }

    public static SimpleStringProperty codeProperty() {
        return code;
    }

    public static void setCode(String code) {
        ApplicatonStore.code.set(code);
    }

    public static String getToken() {
        return token.get();
    }

    public static SimpleStringProperty tokenProperty() {
        return token;
    }

    public static void setToken(String token) {
        ApplicatonStore.token.set(token);
    }

    public static String getName() {
        return name.get();
    }

    public static SimpleStringProperty nameProperty() {
        return name;
    }

    public static void setName(String name) {
        ApplicatonStore.name.set(name);
    }

    public static String getAvatar() {
        return avatar.get();
    }

    public static SimpleStringProperty avatarProperty() {
        return avatar;
    }

    public static void setAvatar(String avatar) {
        ApplicatonStore.avatar.set(avatar);
    }

    public static String getIntroduction() {
        return introduction.get();
    }

    public static SimpleStringProperty introductionProperty() {
        return introduction;
    }

    public static void setIntroduction(String introduction) {
        ApplicatonStore.introduction.set(introduction);
    }


    public static ListProperty<GroupVO> getRoles() {
        if (roles == null) {
            ObservableList<GroupVO> innerList = FXCollections.observableArrayList();
            roles = new SimpleListProperty<>(innerList);
        }
        return roles;
    }


    public static ListProperty<PermissionInfo> getMenus() {
        if (menus == null) {
            ObservableList<PermissionInfo> innerList = FXCollections.observableArrayList();
            menus = new SimpleListProperty<>(innerList);
        }
        return menus;
    }


    public static ListProperty<MenuVO> getAllMenu() {
        if (allMenu == null) {
            ObservableList<MenuVO> innerList = FXCollections.observableArrayList();
            allMenu = new SimpleListProperty<>(innerList);
        }
        return allMenu;
    }

    public static ListProperty<MenuVoCell> getMenuVoCells() {
        if (menuVoCells == null) {
            ObservableList<MenuVoCell> innerList = FXCollections.observableArrayList();
            menuVoCells = new SimpleListProperty<>(innerList);
        }

        return menuVoCells;
    }


    public static ListProperty<PermissionInfo> getElements() {
        if (elements == null) {
            ObservableList<PermissionInfo> innerList = FXCollections.observableArrayList();
            elements = new SimpleListProperty<>(innerList);
        }
        return elements;
    }

    public static MapProperty<String, String> getFeatureMap() {
        if (featureMap == null) {
            ObservableMap<String, String> map = FXCollections.observableMap(new HashMap<>());
            featureMap = new SimpleMapProperty<>(map);
        }
        return featureMap;
    }

    public static ListProperty<MenuVO> getPermissionMenus() {
        if (permissionMenus == null) {
            ObservableList<MenuVO> innerList = FXCollections.observableArrayList();
            permissionMenus = new SimpleListProperty<>(innerList);
        }
        return permissionMenus;
    }

    public static void clearPermissionInfo() {
        setName("");
        getAllMenu().clear();
        getMenuVoCells().clear();
        getMenus().clear();
        ApplicatonStore.getElements().clear();
        getPermissionMenus().clear();
        getRoles().clear();
        getFeatureMap().clear();
    }


}


