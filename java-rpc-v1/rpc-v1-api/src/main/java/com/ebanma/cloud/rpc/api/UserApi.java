package com.ebanma.cloud.rpc.api;

import com.ebanma.cloud.rpc.api.rpc.dto.UserInfoDTO;

public interface UserApi {

    UserInfoDTO getById(Integer id);

}