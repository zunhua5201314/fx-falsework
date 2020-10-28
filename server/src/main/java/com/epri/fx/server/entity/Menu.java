package com.epri.fx.server.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class Menu implements Serializable {
    private Integer id;

    private String code;

    private String title;

    private Integer parentId;

    private String href;

    private String icon;

    private String type;

    private Integer orderNum;

    private String description;

    private String path;

    private String enabled;

    private Date crtTime;

    private String crtUser;

    private String crtName;

    private String crtHost;

    private Date updTime;

    private String updUser;

    private String updName;

    private String updHost;

    private String attr1;

    private String attr2;

    private String attr3;

    private String attr4;

    private String attr5;

    private String attr6;

    private String attr7;

    private String attr8;

    private List<Element> elementList;

    private static final long serialVersionUID = 1L;

    public Menu() {
    }
}