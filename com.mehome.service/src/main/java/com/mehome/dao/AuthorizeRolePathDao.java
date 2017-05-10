package com.mehome.dao;

import com.mehome.domain.AuthorizeMenu;
import com.mehome.domain.AuthorizeRolePath;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AuthorizeRolePathDao {
    int delete(@Param("role") String role, @Param("path") String path);

    int insert(AuthorizeRolePath record);

    int insertRequired(AuthorizeRolePath record);


    /**
     * 根据角色查询可访问的路径
     *
     * @param role
     * @return
     */
    List<String> listByRole(String role);
}