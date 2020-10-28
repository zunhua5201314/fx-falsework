package com.epri.fx.server.vo;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

import java.io.Serializable;
import java.util.Date;


public class GroupTypeVO implements Serializable {

    private SimpleIntegerProperty id = new SimpleIntegerProperty();

    private SimpleStringProperty code = new SimpleStringProperty();

    private SimpleStringProperty name = new SimpleStringProperty();

    private SimpleStringProperty description = new SimpleStringProperty();

    private SimpleObjectProperty updTime = new SimpleObjectProperty();

    private SimpleStringProperty updHost = new SimpleStringProperty();


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

    public String getDescription() {
        return description.get();
    }

    public SimpleStringProperty descriptionProperty() {
        return description;
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

    public Object getUpdTime() {
        return updTime.get();
    }

    public SimpleObjectProperty updTimeProperty() {
        return updTime;
    }

    public void setUpdTime(Object updTime) {
        this.updTime.set(updTime);
    }

    public String getUpdHost() {
        return updHost.get();
    }

    public SimpleStringProperty updHostProperty() {
        return updHost;
    }

    public void setUpdHost(String updHost) {
        this.updHost.set(updHost);
    }

    @Override
    public String toString() {
        return getName();
    }
}