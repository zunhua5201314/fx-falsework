package com.epri.fx.server.vo;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.io.Serializable;

/**
 * @description:
 * @className: GroupVO
 * @author: liwen
 * @date: 2020/7/23 09:54
 */
public class GroupVO implements Serializable {
    private SimpleIntegerProperty id = new SimpleIntegerProperty();

    private SimpleStringProperty code = new SimpleStringProperty();

    private SimpleStringProperty name = new SimpleStringProperty();

    private SimpleIntegerProperty parentId = new SimpleIntegerProperty();

    private SimpleIntegerProperty groupType = new SimpleIntegerProperty();

    private SimpleStringProperty description = new SimpleStringProperty();



    public GroupVO() {
    }

    public int getId() {
        return id.get();
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getCode() {
        return code.get();
    }

    public SimpleStringProperty codeProperty() {
        return code;
    }

    public void setCode(String code) {
        this.code.set(code);
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public int getParentId() {
        return parentId.get();
    }

    public SimpleIntegerProperty parentIdProperty() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId.set(parentId);
    }

    public int getGroupType() {
        return groupType.get();
    }

    public SimpleIntegerProperty groupTypeProperty() {
        return groupType;
    }

    public void setGroupType(int groupType) {
        this.groupType.set(groupType);
    }

    public String getDescription() {
        return description.get();
    }

    public SimpleStringProperty descriptionProperty() {
        return description;
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

    @Override
    public String toString() {
        return getName();
    }
}
