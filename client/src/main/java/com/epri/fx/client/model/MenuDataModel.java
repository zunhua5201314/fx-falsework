package com.epri.fx.client.model;

import com.epri.fx.server.vo.ElementVO;
import com.epri.fx.server.vo.MenuVO;
import io.datafx.controller.injection.scopes.ViewScoped;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

@ViewScoped
public class MenuDataModel {

    private ObjectProperty<MenuVO> selectMenuVOProperty;

    private ListProperty<ElementVO> elementVOS;

    private IntegerProperty selectedElementIndex;

    public ListProperty<ElementVO> getElementVOS() {

        if (elementVOS == null) {
            ObservableList<ElementVO> innerList = FXCollections.observableArrayList();
            elementVOS = new SimpleListProperty<>(innerList);
        }
        return elementVOS;
    }


    public MenuVO getSelectMenuVO() {
        return selectMenuVOPropertyProperty().get();
    }

    public ObjectProperty<MenuVO> selectMenuVOPropertyProperty() {
        if (selectMenuVOProperty == null) {
            selectMenuVOProperty = new SimpleObjectProperty<>(new MenuVO());
        }
        return selectMenuVOProperty;
    }

    public void setSelectMenuVO(MenuVO selectMenuVOProperty) {
        this.selectMenuVOPropertyProperty().set(selectMenuVOProperty);
    }


    public int getSelectedElementIndex() {
        return selectedElementIndexProperty().get();
    }

    public IntegerProperty selectedElementIndexProperty() {

        if (selectedElementIndex == null) {
            selectedElementIndex = new SimpleIntegerProperty();
        }
        return selectedElementIndex;
    }


}


