package com.mehome.dao;

import com.mehome.domain.AuthorizePath;
import org.apache.ibatis.annotations.Param;

public interface AuthorizePathDao {
    int delete(Integer pathId);

    int insert(AuthorizePath record);

    int insertRequired(AuthorizePath record);

    AuthorizePath selectById(Integer pathId);

    int updateRequired(AuthorizePath record);

    int update(AuthorizePath record);

    /**
     * 根据path查找pathId
     * @param path
     * @return
     */
    int getPathIdByPath(@Param("path") String path);
}