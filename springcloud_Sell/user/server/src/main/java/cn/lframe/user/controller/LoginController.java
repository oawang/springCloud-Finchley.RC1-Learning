package cn.lframe.user.controller;

import cn.lframe.user.constant.CookieConstant;
import cn.lframe.user.constant.RedisConstant;
import cn.lframe.user.domain.UserInfo;
import cn.lframe.user.enums.ResultEnum;
import cn.lframe.user.enums.RoleEnum;
import cn.lframe.user.service.UserService;
import cn.lframe.user.util.CookieUtil;
import cn.lframe.user.util.ResultVOUtil;
import cn.lframe.user.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * 买家端登录后写的是openid.
 * 而卖家端登录后写的token=UUID。并且在redis里面写入相应的openid。
 * @author Lframe
 * @create2018 -05 -19 -14:39
 */
@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 买家登录
     *
     * @param openid
     * @param httpServletResponse
     * @return
     */
    @GetMapping("/buyer")
    public ResultVO buyer(@RequestParam("openid") String openid,
                          HttpServletResponse httpServletResponse) {
//        1:openid和数据库里的数据匹配
        UserInfo userInfo = userService.findByOpenid(openid);
        if (userInfo == null) {
            return ResultVOUtil.error(ResultEnum.LOGIN_FAIL);
        }
//        2：判断角色
        if (RoleEnum.BUYER.getCode() != userInfo.getRole()) {
            return ResultVOUtil.error(ResultEnum.ROLE_ERROR);
        }
//        cookie里设置openid=abc
        CookieUtil.set(httpServletResponse, CookieConstant.OPENID, openid, CookieConstant.expire);

        return ResultVOUtil.success();
    }

    /**
     * 卖家登录
     *
     * @param openid
     * @param httpServletResponse
     * @return
     */
    @GetMapping("/seller")
    public ResultVO seller(@RequestParam("openid") String openid,
                           HttpServletRequest httpServletRequest,
                           HttpServletResponse httpServletResponse) {
//        判断是否已经登录
        Cookie cookie = CookieUtil.get(httpServletRequest, CookieConstant.TOKEN);
        if (cookie != null &&
                !StringUtils.isEmpty(
                        stringRedisTemplate.opsForValue().get(
                                String.format(
                                        RedisConstant.TOKEN_TEMPLATE, cookie.getValue()
                                )
                        )
                )
                ) {
            return ResultVOUtil.success();
        }

        //        1:openid和数据库里的数据匹配
        UserInfo userInfo = userService.findByOpenid(openid);
        if (userInfo == null) {
            return ResultVOUtil.error(ResultEnum.LOGIN_FAIL);
        }
//        2：判断角色
        if (RoleEnum.SELLER.getCode() != userInfo.getRole()) {
            return ResultVOUtil.error(ResultEnum.ROLE_ERROR);
        }
//        3:redis设置key=UUID ，value=xyz
        String token = UUID.randomUUID().toString();
        Integer expire = CookieConstant.expire;
//        把token_%s（%s代表了UUID（一个随机字符串））作为redis的key，卖家的openid作为value存储到了Redis中。
        stringRedisTemplate.opsForValue().set(String.format(RedisConstant.TOKEN_TEMPLATE, token),
                openid,
                expire,
                TimeUnit.SECONDS);

//        4:设置cookie的name=token value=token（上面的随机字符串UUID）
        CookieUtil.set(httpServletResponse, CookieConstant.TOKEN, token, CookieConstant.expire);

        return ResultVOUtil.success();
    }
}
