package com.sama.ledger.config;

import com.core4ct.api.system.OrgDubboService;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

/**
 * 缓存配置
 * @author: huxh
 * @description:
 * @datetime: 2025/9/17 13:17
 */
@Configuration
public class CaffeineConfig {

    @DubboReference
    OrgDubboService orgDubboService;

    @Bean(name = "orgCodeAndNameCache")
    public LoadingCache<String, String> orgCodeAndNameCache() {
        return Caffeine.newBuilder()
            // 基于写入时间
            .expireAfterWrite(1, TimeUnit.DAYS)
            .initialCapacity(100)
            .maximumSize(5000)
            .build(orgCode -> {
                String orgName = orgDubboService.getOrganizeName(orgCode);
                return orgName != null ? orgName : "未定义";
            });
    }

}
