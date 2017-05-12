package com.mehome.dao;

import com.mehome.domain.AuthorizeAdminPath;
import com.mehome.domain.AuthorizePath;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AuthorizeAdminPathDao {
    /**
     * 删除某一个管理员下面的所有路径
     *
     * @param adminId 管理员ID
     * @return
     */
    int delete(@Param("adminId") Integer adminId);

    /**
     * 根据管理员ID查询路径ID列表
     *
     * @param adminId 管理员ID
     * @return
     */
    List<Integer> selectPathIdByAdminId(@Param("adminId") Integer adminId);

    /**
     * 根据管理员ID查询路径列表
     *
     * @param adminId 管理员ID
     * @return
     */
    List<String> selectPathByAdminId(@Param("adminId") Integer adminId);

    /**
     * 批量给管理员新增菜单
     *
     * @param adminId 管理员ID
     * @param pathIds 菜单IDs
     * @return
     */
    int insertPathByAdmin(@Param("adminId") Integer adminId, @Param("pathIds") List<Integer> pathIds);

    /**
     * 插入单个管理员路径
     *
     * @param adminId
     * @param pathId
     * @return
     */
    int insertRequired(@Param("adminId") Integer adminId, @Param("pathId") Integer pathId);


    int updateRequired(@Param("adminId") Integer adminId, @Param("pathId") Integer pathId);

}