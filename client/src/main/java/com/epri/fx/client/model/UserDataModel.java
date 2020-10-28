package com.epri.fx.client.model;

import com.epri.fx.server.vo.UserVO;
import io.datafx.controller.injection.scopes.FlowScoped;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

@FlowScoped
public class UserDataModel {
    private int counter = 0;
    private ListProperty<UserVO> users;

    private IntegerProperty pageCount;
    private IntegerProperty selectedPersonIndex;


    public ListProperty<UserVO> getUsers() {
        if (users == null) {
            ObservableList<UserVO> innerList = FXCollections.observableArrayList();
            users = new SimpleListProperty<>(innerList);
        }
        return users;
    }

    public int getSelectedPersonIndex() {
        return selectedPersonIndexProperty().get();
    }

    public void setSelectedPersonIndex(int selectedPersonIndex) {
        this.selectedPersonIndex.set(selectedPersonIndex);
    }

    public IntegerProperty selectedPersonIndexProperty() {
        if (selectedPersonIndex == null) {
            selectedPersonIndex = new SimpleIntegerProperty();
        }
        return selectedPersonIndex;
    }

    public int getPageCount() {
        return pageCount.get();
    }

    public IntegerProperty pageCountProperty() {
        if (pageCount == null) {
            pageCount = new SimpleIntegerProperty();
        }
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount.set(pageCount);
    }
}