package com.mehome.dao;

import com.mehome.domain.AuthorizeAdminMenu;
import com.mehome.domain.AuthorizeMenu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AuthorizeAdminMenuDao {

    /**
     * 给管理员新增一个菜单
     *
     * @param adminId
     * @param menuId
     * @return
     */
    int insertRequired(@Param("adminId") Integer adminId, @Param("menuId") Integer menuId);

    /**
     * 根据管理员ID删除管理员的所有菜单
     *
     * @param adminId 管理员ID
     * @return
     */
    int deleteByAdmin(@Param("adminId") Integer adminId);

    /**
     * 批量给管理员新增菜单
     *
     * @param adminId 管理员ID
     * @param menuIds 菜单IDs
     * @return
     */
    int insertMenuByAdmin(@Param("adminId") Integer adminId, @Param("menuIds") List<Integer> menuIds);

    /**
     * 根据管理员ID查询菜单ID列表
     *
     * @param adminId 管理员ID
     * @return
     */
    List<Integer> selectMenuIdByAdmin(@Param("adminId") Integer adminId);

    /**
     * 根据管理员ID查询菜单列表
     *
     * @param adminId 管理员ID
     * @return
     */
    List<AuthorizeMenu> selectMenuByAdmin(@Param("adminId") Integer adminId);

    /**
     * 更新管理员的某一个菜单ID
     *
     * @param adminId
     * @param menuId
     * @return
     */
    int updateRequired(@Param("adminId") Integer adminId, @Param("menuId") Integer menuId);
}