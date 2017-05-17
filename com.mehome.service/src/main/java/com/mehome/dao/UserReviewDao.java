package com.mehome.dao;

import com.mehome.domain.UserReview;
import org.apache.ibatis.annotations.Param;

public interface UserReviewDao {
    int delete(Integer autoId);

    int insert(UserReview record);

    int insertRequired(UserReview record);

    UserReview selectById(Integer autoId);

    int updateRequired(UserReview record);

    int update(UserReview record);

    long getCompanyPassedNum(@Param("companyId") Integer companyId);
}