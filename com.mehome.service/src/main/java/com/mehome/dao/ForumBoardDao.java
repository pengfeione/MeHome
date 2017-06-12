package com.mehome.dao;

import com.mehome.domain.ForumBoard;

public interface ForumBoardDao {
    int delete(Integer bid);

    int insert(ForumBoard record);

    int insertRequired(ForumBoard record);

    ForumBoard selectById(Integer bid);

    int updateRequired(ForumBoard record);

    int update(ForumBoard record);
}