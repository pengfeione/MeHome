package com.mehome.service.iface;

import com.mehome.domain.HouseRentPiece;
import com.mehome.requestDTO.HouseRentPieceDTO;

import java.util.List;

public interface IHouseRentPieceService {
    int delete(Long autoId);

    int insertRequired(HouseRentPiece record);

    HouseRentPiece selectById(Long autoId);

    int updateRequired(HouseRentPiece record);

    public List<HouseRentPiece> listByCondition(HouseRentPieceDTO houseRentPieceDTO);

    public Long countByCondition(HouseRentPieceDTO houseRentPieceDTO);

}