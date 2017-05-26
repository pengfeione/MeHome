package com.mehome.service.impl;

import com.mehome.dao.CompanyListDao;
import com.mehome.dao.UserInfoDao;
import com.mehome.domain.CompanyList;
import com.mehome.domain.UserInfo;
import com.mehome.requestDTO.UserInfoDTO;
import com.mehome.requestDTO.UserRequestDTO;
import com.mehome.resonpseDTO.UserDTO;
import com.mehome.service.iface.IPlatformService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/5/26.
 */
@Service("IPlatformService")
public class PlatformServiceImpl implements IPlatformService {
    @Autowired
    private CompanyListDao companyListDao;
    @Autowired
    private UserInfoDao userInfoDao;

    @Override
    public List<UserInfo> listByCondition(UserInfoDTO userInfoDTO) {
        //用户ID，用户真实姓名，注册手机号，公司ID
        List<UserInfo> result = userInfoDao.listByCondition(userInfoDTO);
        if (null == result || result.size() <= 0) {
            return new ArrayList<UserInfo>();
        }
        for (UserInfo item : result) {
            if (null != item.getCompanyId() && 0 != item.getCompanyId()) {
                CompanyList companyList = companyListDao.selectById(item.getCompanyId());
                item.setCompanyName(companyList.getCompanyName());
            }
        }
        return result;
    }

    @Override
    public long countByCondition(UserInfoDTO userInfoDTO) {
        Long ret = userInfoDao.countByCondition(userInfoDTO);
        if (null == ret) {
            return 0l;
        } else {
            return ret;
        }
    }
}
