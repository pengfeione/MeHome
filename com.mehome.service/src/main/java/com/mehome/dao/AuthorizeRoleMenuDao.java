package com.mehome.dao;

import com.mehome.domain.AuthorizeMenu;
import com.mehome.domain.AuthorizeRoleMenu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AuthorizeRoleMenuDao {
    int delete(@Param("role") String role, @Param("menuId") Integer menuId);

    int insert(AuthorizeRoleMenu record);

    int insertRequired(AuthorizeRoleMenu record);

    /**
     * 根据角色查询可见的菜单
     * @param role
     * @return
     */
    List<AuthorizeMenu> listByRole(String role);
}