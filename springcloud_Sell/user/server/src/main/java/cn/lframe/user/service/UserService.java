package cn.lframe.user.service;

import cn.lframe.user.domain.UserInfo;

public interface UserService {

    /**
     * 通过openid查询用户信息
     * @param openid
     * @return
     */
    UserInfo findByOpenid(String openid);
}
