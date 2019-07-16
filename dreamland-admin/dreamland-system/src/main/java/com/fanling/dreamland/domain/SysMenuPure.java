package com.fanling.dreamland.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class SysMenuPure {
    private String menuId;
    //菜单名称
    private String menuName;
    //父菜单名称
    private String parentName;
    //父菜单ID
    private String parentId;
    //菜单URL
    private String path;
    // 菜单的vue组件路径
    private String component;
    //默认路径
    private String redirect;
    //子菜单
    private List<SysMenuPure> children = new ArrayList<>(5);
}
