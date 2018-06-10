package cn.lframe.apigateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_DECORATION_FILTER_ORDER;

/**
 * 使用Pre filter做权限校验
 * 这里是指如果用户的请求中需要添加“？token=数字”，才能方法所有的微服务。
 *
 * @author Lframe
 * @create2018 -05 -19 -8:44
 */
@Component
public class TokenFilter extends ZuulFilter {

    /**
     * 下面的枚举常量都是取至FilterConstants类。
     */
    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    /**
     * 人家说这是官方提倡的写法，以后看看官方文档，
     *
     * @return
     */
    @Override
    public int filterOrder() {
        return PRE_DECORATION_FILTER_ORDER - 1;
    }
    @Override
    public boolean shouldFilter() {
        return true;
    }
    /**
     * 这里就是我们实现拦截的逻辑
     *
     * @return
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest httpServletRequest = requestContext.getRequest();
//        System.out.println("请求URL：************"+httpServletRequest.getRequestURI());
//        从URL参数中获取token，也可以从cookie、header里获取。
        String token = httpServletRequest.getParameter("token");
        if (StringUtils.isEmpty(token)){
//            设置为false，表示响应不通过
//            requestContext.setSendZuulResponse(false);
//            401状态码表示权限不足。UNAUTHORIZED表示未授权
//            requestContext.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());


        }
        return null;
    }
}
