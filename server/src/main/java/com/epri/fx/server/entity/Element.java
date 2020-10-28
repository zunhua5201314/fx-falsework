package com.epri.fx.server.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Element implements Serializable {
    private Integer id;

    private String code;

    private String type;

    private String name;

    private String uri;

    private String menuId;

    private String parentId;

    private String path;

    private String method;

    private String description;

    private Date crtTime;

    private String crtUser;

    private String crtName;

    private String crtHost;

    private String attr1;

    private String attr2;

    private String attr3;

    private String attr4;

    private String attr5;

    private String attr6;

    private String attr7;

    private String attr8;

    private static final long serialVersionUID = 1L;

    public Element() {
    }
}