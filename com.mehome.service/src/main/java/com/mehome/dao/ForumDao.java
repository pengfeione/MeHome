package com.mehome.dao;

import com.mehome.domain.Forum;

public interface ForumDao {
    int delete(String fid);

    int insert(Forum record);

    int insertRequired(Forum record);

    Forum selectById(String fid);

    int updateRequired(Forum record);

    int update(Forum record);
}