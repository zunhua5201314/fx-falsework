package com.epri.fx.server.vo;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class ElementVO {

    private SimpleIntegerProperty id = new SimpleIntegerProperty();

    private SimpleStringProperty code = new SimpleStringProperty();

    private SimpleStringProperty type = new SimpleStringProperty();

    private SimpleStringProperty name = new SimpleStringProperty();

    private SimpleStringProperty uri = new SimpleStringProperty();

    private SimpleStringProperty menuId = new SimpleStringProperty();

    private SimpleStringProperty parentId = new SimpleStringProperty();

    private SimpleStringProperty path = new SimpleStringProperty();

    private SimpleStringProperty method = new SimpleStringProperty();

    private SimpleStringProperty description = new SimpleStringProperty();

    private SimpleBooleanProperty sel = new SimpleBooleanProperty(false);


    public ElementVO() {
    }

    public ElementVO(Integer id, String code, String type, String name, String uri, String menuId, String parentId, String path, String method, String description) {
        this.id.set(id);
        this.code.set(code);
        this.type.set(type);
        this.name.set(name);
        this.uri.set(uri);
        this.menuId.set(menuId);
        this.parentId.set(parentId);
        this.path.set(path);
        this.method.set(method);
        this.description.set(description);
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

    public String getType() {
        return type.get();
    }

    public SimpleStringProperty typeProperty() {
        return type;
    }

    public void setType(String type) {
        this.type.set(type);
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

    public String getUri() {
        return uri.get();
    }

    public SimpleStringProperty uriProperty() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri.set(uri);
    }

    public String getMenuId() {
        return menuId.get();
    }

    public SimpleStringProperty menuIdProperty() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId.set(menuId);
    }

    public String getParentId() {
        return parentId.get();
    }

    public SimpleStringProperty parentIdProperty() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId.set(parentId);
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

    public String getMethod() {
        return method.get();
    }

    public SimpleStringProperty methodProperty() {
        return method;
    }

    public void setMethod(String method) {
        this.method.set(method);
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

    public int getId() {
        return id.get();
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
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
}