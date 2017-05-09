package com.mehome.dao;

import com.mehome.domain.ProductComment;

public interface ProductCommentDao {
    int delete(String commentId);

    int insert(ProductComment record);

    int insertRequired(ProductComment record);

    ProductComment selectById(String commentId);

    int updateRequired(ProductComment record);

    int updateByPrimaryKeyWithBLOBs(ProductComment record);

    int update(ProductComment record);
}