package com.mehome.dao;

import java.util.List;

import com.mehome.domain.ProductComment;
import com.mehome.requestDTO.CommentBean;
public interface ProductCommentDao {
    int delete(String commentId);

    int insert(ProductComment record);

    int insertRequired(ProductComment record);

    ProductComment selectById(String commentId);

    int updateRequired(ProductComment record);

    int updateByPrimaryKeyWithBLOBs(ProductComment record);

    int update(ProductComment record);
    
    public List<ProductComment> getListByCondition(CommentBean bean);
    
    Long getSizeByCondition(CommentBean bean);
}