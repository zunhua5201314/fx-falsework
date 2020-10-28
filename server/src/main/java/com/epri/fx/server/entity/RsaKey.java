package com.epri.fx.server.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class RsaKey implements Serializable {
    private String key;

    private String value;

    private static final long serialVersionUID = 1L;

}