package cn.lframe.user.repository;

import cn.lframe.user.domain.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Lframe
 * @create2018 -05 -19 -14:31
 */
public interface UserInfoRepository extends JpaRepository<UserInfo, String> {

    UserInfo findByOpenid(String openid);
}
