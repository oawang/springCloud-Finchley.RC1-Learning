package cn.lframe.user.service;

import cn.lframe.user.UserApplicationTests;
import cn.lframe.user.domain.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@Slf4j
public class UserServiceTest extends UserApplicationTests {

    @Autowired
    private UserService userService;

    @Test
    public void findByOpenid() {
        Assert.assertNotNull(userService.findByOpenid("abc"));
        log.info("【openid为abc的用户的详细信息】：{}",userService.findByOpenid("abc"));
    }
}