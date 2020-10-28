package com.epri.fx.client.model;

import com.epri.fx.server.vo.GroupTypeVO;
import io.datafx.controller.injection.scopes.FlowScoped;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

@FlowScoped
public class GroupTypeDataModel {
    private int counter = 0;
    private ListProperty<GroupTypeVO> groupTypes;
    private IntegerProperty selectedIndex;

    public ListProperty<GroupTypeVO> getGroupTypes() {
        if (groupTypes == null) {
            ObservableList<GroupTypeVO> innerList = FXCollections.observableArrayList();
            groupTypes = new SimpleListProperty<>(innerList);
        }
        return groupTypes;
    }

    public int getSelectedIndex() {
        return selectedIndexProperty().get();
    }

    public void setSelectedIndex(int selectedPersonIndex) {
        this.selectedIndex.set(selectedPersonIndex);
    }

    public IntegerProperty selectedIndexProperty() {
        if (selectedIndex == null) {
            selectedIndex = new SimpleIntegerProperty();
        }
        return selectedIndex;
    }


}