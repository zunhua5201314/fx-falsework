package com.epri.fx.client.bean;

import com.epri.fx.server.entity.Menu;
import com.epri.fx.server.vo.MenuVO;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

/**
 * @description:
 * @className: MenuVoCell
 * @author: liwen
 * @date: 2020/8/17 20:08
 */

public class MenuVoCell {
    private ObjectProperty<MenuVO> menuVO;
    private ObservableList<MenuVO> childrenMenus;

    public MenuVoCell(MenuVO menuVO, List<MenuVO> childrenMenus) {
        this.menuVO = new SimpleObjectProperty<>(menuVO);
        if (childrenMenus != null) {
            getChildrenMenus().addAll(childrenMenus);
        }

    }


    public MenuVO getMenuVO() {
        return menuVO.get();
    }

    public ObjectProperty<MenuVO> menuVOProperty() {
        return menuVO;
    }

    public ObservableList<MenuVO> getChildrenMenus() {
        if (childrenMenus == null) {
            ObservableList<MenuVO> innerList = FXCollections.observableArrayList();
            childrenMenus = new SimpleListProperty<>(innerList);
        }

        return childrenMenus;
    }
}