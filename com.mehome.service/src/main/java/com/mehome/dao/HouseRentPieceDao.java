package com.mehome.dao;

import com.mehome.domain.HouseRentPiece;

public interface HouseRentPieceDao {
    int delete(Long autoId);

    int insert(HouseRentPiece record);

    int insertRequired(HouseRentPiece record);

    HouseRentPiece selectById(Long autoId);

    int updateRequired(HouseRentPiece record);

    int update(HouseRentPiece record);
}