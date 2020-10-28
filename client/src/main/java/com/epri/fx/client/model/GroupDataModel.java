package com.epri.fx.client.model;

import com.epri.fx.server.vo.ElementVO;
import com.epri.fx.server.vo.GroupVO;
import com.epri.fx.server.vo.MenuVO;
import io.datafx.controller.FxmlLoadException;
import io.datafx.controller.injection.scopes.ViewScoped;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * @description:
 * @className: GroupDataModel
 * @author: liwen
 * @date: 2020/7/22 16:42
 */
@ViewScoped
public class GroupDataModel {


    private ListProperty<MenuVO> menuVOS;
    private SimpleObjectProperty<GroupVO> selectedGroupVO = new SimpleObjectProperty<>(new GroupVO());
    private SimpleObjectProperty<MenuVO> selectedMenuVO = new SimpleObjectProperty<>(new MenuVO());


    public ObservableList<ElementVO> getElementVOS() {
        return FXCollections.observableArrayList(getSelectedMenuVO().getElementVOS());
    }

    public ListProperty<MenuVO> getMenuVOS() {
        if (menuVOS == null) {
            ObservableList<MenuVO> innerList = FXCollections.observableArrayList();
            menuVOS = new SimpleListProperty<>(innerList);
        }
        return menuVOS;
    }

    public GroupVO getSelectedGroup() {
        return selectedGroupVO.get();
    }

    public ObjectProperty<GroupVO> selectedGroupProperty() {
        return selectedGroupVO;
    }

    public void setSelectedGroup(GroupVO group) {
        this.selectedGroupVO.set(group);
    }

    public MenuVO getSelectedMenuVO() {
        return selectedMenuVO.get();
    }

    public SimpleObjectProperty<MenuVO> selectedMenuVOProperty() {
        return selectedMenuVO;
    }

    public void setSelectedMenuVO(MenuVO selectedMenuVO) {
        this.selectedMenuVO.set(selectedMenuVO);
    }
}
