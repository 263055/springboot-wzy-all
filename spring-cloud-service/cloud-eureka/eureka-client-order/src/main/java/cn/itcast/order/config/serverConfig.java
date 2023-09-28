package cn.itcast.order.config;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * @author : wzy
 * @since : 2023/9/27 11:32
 * <p>
 *
 * <p>
 * Created in IDEA
 */

@Component
public class serverConfig {

    /**
     * 创建RestTemplate并注入Spring容器
     */
    @Bean
    @LoadBalanced // 负载均衡
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    /*
     * 通过 ribbon 配置负载均衡
     * 这里使用 RandomRule 意为: 随机策略
     * 可以注释掉, ribbon 默认使用 RoundRobinRule
     */
     @Bean
     public IRule randomRule() {
         return new RandomRule();
     }
}
