package cn.lframe.apigateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

/**
 * 跨域配置
 * C - Cross  O - Origin  R - Resource  S - Sharing
 *
 * @author Lframe
 * @create2018 -05 -20 -9:38
 */
@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter(){
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        final CorsConfiguration config = new CorsConfiguration();

//        设置支持cookie跨域
        config.setAllowCredentials(true);

//        放哪些原始域（允许的原始域），例如 http://www.a.com   *代表全部允许
        config.setAllowedOrigins(Arrays.asList("*"));

//        设置允许的头   *代表全部允许
//        config.setExposedHeaders(Arrays.asList("*"));

//        设置允许访问的方法 GET、POST等等   * 代表全部允许
        config.setAllowedMethods(Arrays.asList("*"));

//        缓存时间，在下面设置的时间段内，对于相同的跨域请求，它就不会再进行检查了.单位是秒
        config.setMaxAge(300l);

        source.registerCorsConfiguration("/**",config);

        return new CorsFilter(source);
    }

}
