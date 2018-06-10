package cn.lframe.apigateway.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Lframe
 * @create2018 -05 -19 -15:13
 */
public class CookieUtil {

    /**
     * 设置cookie
     *
     * @param httpServletResponse
     * @param name
     * @param value
     * @param maxAge
     */
    public static void set(HttpServletResponse httpServletResponse,
                           String name,
                           String value,
                           int maxAge) {
        Cookie cookie = new Cookie(name, value);
        cookie.setPath("/");
        cookie.setMaxAge(maxAge);
        httpServletResponse.addCookie(cookie);
    }

    public static Cookie get(HttpServletRequest httpServletRequest,
                             String name) {
        Cookie[] cookies = httpServletRequest.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (name.equals(cookie.getName())) {
                    return cookie;
                }
            }
        }
        return null;
    }

}
