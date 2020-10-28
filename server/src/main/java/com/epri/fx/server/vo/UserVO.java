package com.epri.fx.server.vo;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class UserVO {

    private SimpleStringProperty name = new SimpleStringProperty();
    private SimpleStringProperty userName = new SimpleStringProperty();
    private SimpleStringProperty description = new SimpleStringProperty();
    private SimpleStringProperty updTime = new SimpleStringProperty();
    private SimpleStringProperty updUser = new SimpleStringProperty();
    private SimpleStringProperty sex = new SimpleStringProperty();
    private SimpleIntegerProperty id = new SimpleIntegerProperty();

    public UserVO() {
    }

    public UserVO(Integer id, String name, String userName, String updTime, String updUser, String sex, String description) {
        this.name = new SimpleStringProperty(name);
        this.userName = new SimpleStringProperty(userName);
        this.description = new SimpleStringProperty(description);
        this.updTime = new SimpleStringProperty(updTime);
        this.updUser = new SimpleStringProperty(updUser);
        this.sex = new SimpleStringProperty(sex);
        this.id = new SimpleIntegerProperty(id);
        ;
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

    public String getUserName() {
        return userName.get();
    }

    public SimpleStringProperty userNameProperty() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName.set(userName);
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

    public String getUpdTime() {
        return updTime.get();
    }

    public SimpleStringProperty updTimeProperty() {
        return updTime;
    }

    public void setUpdTime(String updTime) {
        this.updTime.set(updTime);
    }

    public String getUpdUser() {
        return updUser.get();
    }

    public SimpleStringProperty updUserProperty() {
        return updUser;
    }

    public void setUpdUser(String updUser) {
        this.updUser.set(updUser);
    }

    public String getSex() {
        return sex.get();
    }

    public SimpleStringProperty sexProperty() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex.set(sex);
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

    @Override
    public String toString() {
        return getUserName();
    }
}