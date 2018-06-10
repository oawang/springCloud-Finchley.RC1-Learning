package cn.lframe.apigateway.filter;

import cn.lframe.apigateway.exception.RateLimitException;
import com.google.common.util.concurrent.RateLimiter;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

/**
 * 限流拦截器
 *
 * @author Lframe
 * @create2018 -05 -19 -9:57
 */
@Component
public class RateLimiterFilter extends ZuulFilter {

    //    它是google中的Guva组件的令牌桶算法的实现
//    每秒放100个令牌
    private static final RateLimiter RATE_LIMITER = RateLimiter.create(100);

    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return FilterConstants.SERVLET_DETECTION_FILTER_ORDER - 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        if (!RATE_LIMITER.tryAcquire()){
//            如果没有获取到令牌，你也可以返回一个HTTP状态码给前端。（和TokenFilter的做法一样）
//            这里我们使用另外一种方式，异常处理机制：
            throw new RateLimitException();
        }
            return null;
    }
}
