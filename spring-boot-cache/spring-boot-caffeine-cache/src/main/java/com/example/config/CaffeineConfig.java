package com.example.config;

import com.example.dto.ItemStock;
import com.example.dto.UserDto;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * caffeine缓存配置
 * - 优点：可以针对每个cache配置不同的参数，比如过期时长、最大容量（定制化配置）
 * </p>
 *
 * @author MrWen
 **/
@Configuration
@EnableCaching
public class CaffeineConfig {

    /**
     * 每个容器最大个数
     */
    private static final int DEFAULT_MAXSIZE = 1000;

    /**
     * 每个容器的过期时间（单位秒）
     */
    private static final int DEFAULT_TTL = 3600;

    /**
     * 个性化配置缓存
     */
    @Bean
    public CacheManager cacheManager() {
        SimpleCacheManager manager = new SimpleCacheManager();
        //把各个cache注册到cacheManager中，CaffeineCache实现了org.springframework.cache.Cache接口
        ArrayList<CaffeineCache> caches = new ArrayList<>();
        for (Caches c : Caches.values()) {
            caches.add(new CaffeineCache(c.name(),
                    Caffeine.newBuilder().recordStats()
                            .expireAfterWrite(c.getTtl(), TimeUnit.SECONDS)
                            .maximumSize(c.getMaxSize())
                            .build())
            );
        }
        manager.setCaches(caches);
        return manager;
    }

    /**
     * 定义cache名称、超时时长秒、最大个数
     * 每个cache缺省3600秒过期，最大个数1000
     */
    public enum Caches {
        admin(2, 2),
        info(5),
        role;

        private int maxSize = DEFAULT_MAXSIZE;    //最大數量
        private int ttl = DEFAULT_TTL;        //过期时间（秒）

        Caches() {
        }

        Caches(int ttl) {
            this.ttl = ttl;
        }

        Caches(int ttl, int maxSize) {
            this.ttl = ttl;
            this.maxSize = maxSize;
        }

        public int getMaxSize() {
            return maxSize;
        }

        public void setMaxSize(int maxSize) {
            this.maxSize = maxSize;
        }

        public int getTtl() {
            return ttl;
        }

        public void setTtl(int ttl) {
            this.ttl = ttl;
        }
    }


    /**
     * 简单的
     */
    @Bean
    public Cache<Long, UserDto> userDtoCache() {
        return Caffeine.newBuilder()
                //初始容量
                .initialCapacity(100)
                //最大容量
                .maximumSize(10_000)
                .build();
    }

    /**
     * 复杂的
     */
    @Bean
    public Cache<Long, ItemStock> stockCache() {
        // 构建cache对象
        return Caffeine.newBuilder()
                //0) 驱逐策略：基于容量，时间，引用。
                //todo 自定义选择，不建议引用模式
                //0.1 基于时间
                .expireAfterWrite(10, TimeUnit.MINUTES)
                //0.2.1 基于容量
                //初始容量
                .initialCapacity(100)
                .maximumSize(10_000)
//                //0.2.2 权重
//                .weigher(((key, value) -> {
//                    if (key.equals(1)) {
//                        return 1;
//                    } else {
//                        return 2;
//                    }
//                }))
                //0.3 基于引用
                //0.3.1 当进行GC的时候进行驱逐
//                .softValues()
                //0.3.2 当key和缓存元素都不再存在其他强引用的时候驱逐
//                .weakKeys()
//                .weakValues()

                .build();
    }
}
