package com.fanling.dreamland.utils;


import com.fanling.dreamland.domain.SysMenuPure;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TreeUtils {

    /**
     * 根据父节点的ID获取所有子节点
     *
     * @param list     分类表
     * @param parentId 传入的父节点ID
     * @return
     */
    public static List<SysMenuPure> getChildPerms(List<SysMenuPure> list, String parentId) {
        List<SysMenuPure> returnList = new ArrayList<>();
        for (Iterator<SysMenuPure> iterator = list.iterator(); iterator.hasNext(); ) {
            SysMenuPure t = iterator.next();
            // 一、根据传入的某个父节点ID,遍历该父节点的所有子节点
            if (parentId.equals(t.getParentId())) {
                recursionFn(list, t);
                returnList.add(t);
            }
        }
        return returnList;
    }

    /**
     * 递归列表
     *
     * @param list
     * @param t
     */
    private static void recursionFn(List<SysMenuPure> list, SysMenuPure t) {
        // 得到子节点列表
        List<SysMenuPure> childList = getChildList(list, t);
        t.setChildren(childList);
        for (SysMenuPure tChild : childList) {
            if (hasChild(list, tChild)) {
                // 判断是否有子节点
                Iterator<SysMenuPure> it = childList.iterator();
                while (it.hasNext()) {
                    SysMenuPure n = it.next();
                    recursionFn(list, n);
                }
            }
        }
    }

    /**
     * 判断是否有子节点
     */
    private static boolean hasChild(List<SysMenuPure> list, SysMenuPure t) {
        return getChildList(list, t).size() > 0;
    }

    /**
     * 得到子节点列表
     */
    private static List<SysMenuPure> getChildList(List<SysMenuPure> list, SysMenuPure t) {

        List<SysMenuPure> tlist = new ArrayList<>();
        Iterator<SysMenuPure> it = list.iterator();
        while (it.hasNext()) {
            SysMenuPure n = it.next();
            if (n.getParentId().equals(t.getMenuId())) {
                tlist.add(n);
            }
        }
        return tlist;
    }
}
