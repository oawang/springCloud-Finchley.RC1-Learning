package cn.lframe.order.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

/**
 * 用于测试@RefreshScope的使用
 * @author Lframe
 * @create2018 -05 -09 -10:49
 */
@ConfigurationProperties("girl")
@Component
@Data
@RefreshScope
public class GirlConfig {

    private String name;

    private Integer age;
}
