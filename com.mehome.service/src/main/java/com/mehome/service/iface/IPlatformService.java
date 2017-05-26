package com.mehome.service.iface;

import com.mehome.domain.UserInfo;
import com.mehome.requestDTO.UserInfoDTO;
import com.mehome.requestDTO.UserRequestDTO;
import com.mehome.resonpseDTO.UserDTO;

import java.util.List;

/**
 * Created by Administrator on 2017/5/26.
 */
public interface IPlatformService {
    public List<UserInfo> listByCondition(UserInfoDTO userInfoDTO);

    public long countByCondition(UserInfoDTO userInfoDTO);
}
