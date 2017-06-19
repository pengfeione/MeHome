package com.mehome.service.impl;

import com.mehome.dao.HouseRentPieceDao;
import com.mehome.domain.HouseRentPiece;
import com.mehome.requestDTO.HouseRentPieceDTO;
import com.mehome.service.iface.IHouseRentPieceService;
import com.mehome.utils.AssertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("IHouseRentPieceService")
public class HouseRentPieceService implements IHouseRentPieceService {
    @Autowired
    private HouseRentPieceDao pieceDao;

    @Override
    public int delete(Long autoId) {
        return pieceDao.delete(autoId);
    }

    @Override
    public int insertRequired(HouseRentPiece record) {
        return pieceDao.insertRequired(record);
    }

    @Override
    public HouseRentPiece selectById(Long autoId) {
        return pieceDao.selectById(autoId);
    }

    @Override
    public int updateRequired(HouseRentPiece record) {
        AssertUtils.isNotNull(record.getAutoId(), "更新标识不能为空！");
        return pieceDao.updateRequired(record);
    }

    @Override
    public List<HouseRentPiece> listByCondition(HouseRentPieceDTO houseRentPieceDTO) {
        return pieceDao.listByCondition(houseRentPieceDTO);
    }

    @Override
    public Long countByCondition(HouseRentPieceDTO houseRentPieceDTO) {
        Long ret = pieceDao.countByCondition(houseRentPieceDTO);
        if (null == ret) {
            return 0l;
        }
        return ret;
    }
}
