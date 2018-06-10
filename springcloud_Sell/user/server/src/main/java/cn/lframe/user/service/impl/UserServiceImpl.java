package cn.lframe.user.service.impl;

import cn.lframe.user.domain.UserInfo;
import cn.lframe.user.repository.UserInfoRepository;
import cn.lframe.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Lframe
 * @create2018 -05 -19 -14:36
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserInfoRepository repository;

    /**
     * 通过openid查询用户信息
     *
     * @param openid
     * @return
     */
    @Override
    public UserInfo findByOpenid(String openid) {
        return repository.findByOpenid(openid);
    }
}
