package com.epri.fx.server.vo;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MenuVO implements Serializable {

    private SimpleIntegerProperty id = new SimpleIntegerProperty();

    private SimpleStringProperty code = new SimpleStringProperty();

    private SimpleStringProperty title = new SimpleStringProperty();

    private SimpleIntegerProperty parentId = new SimpleIntegerProperty();

    private SimpleStringProperty href = new SimpleStringProperty();

    private SimpleStringProperty icon = new SimpleStringProperty();

    private SimpleStringProperty type = new SimpleStringProperty();

    private SimpleIntegerProperty orderNum = new SimpleIntegerProperty();

    private SimpleStringProperty description = new SimpleStringProperty();

    private SimpleStringProperty path = new SimpleStringProperty();

    private SimpleStringProperty enabled = new SimpleStringProperty();

    private SimpleStringProperty node = new SimpleStringProperty();

    private SimpleBooleanProperty sel = new SimpleBooleanProperty(false);

    private List<ElementVO> elementVOS;

    public MenuVO() {
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

    public String getTitle() {
        return title.get();
    }

    public SimpleStringProperty titleProperty() {
        return title;
    }

    public void setTitle(String title) {
        this.title.set(title);
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

    public String getHref() {
        return href.get();
    }

    public SimpleStringProperty hrefProperty() {
        return href;
    }

    public void setHref(String href) {
        this.href.set(href);
    }

    public String getIcon() {
        return icon.get();
    }

    public SimpleStringProperty iconProperty() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon.set(icon);
    }

    public String getType() {
        return type.get();
    }

    public SimpleStringProperty typeProperty() {
        return type;
    }

    public void setType(String type) {
        this.type.set(type);
    }

    public Integer getOrderNum() {
        return orderNum.get();
    }

    public SimpleIntegerProperty orderNumProperty() {
        return orderNum;
    }

    public void setOrderNum(int orderNum) {
        this.orderNum.set(orderNum);
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

    public String getPath() {
        return path.get();
    }

    public SimpleStringProperty pathProperty() {
        return path;
    }

    public void setPath(String path) {
        this.path.set(path);
    }

    public String getEnabled() {
        return enabled.get();
    }

    public SimpleStringProperty enabledProperty() {
        return enabled;
    }

    public void setEnabled(String enabled) {
        this.enabled.set(enabled);
    }

    public String getNode() {
        return node.get();
    }

    public SimpleStringProperty nodeProperty() {
        return node;
    }

    public void setNode(String node) {
        this.node.set(node);
    }

    public boolean isSel() {
        return sel.get();
    }

    public SimpleBooleanProperty selProperty() {
        return sel;
    }

    public void setSel(boolean sel) {
        this.sel.set(sel);
    }

    public List<ElementVO> getElementVOS() {

        if (elementVOS == null) {
            elementVOS = new ArrayList<>();
        }
        return elementVOS;
    }

    @Override
    public String toString() {
        return getTitle();
    }
}