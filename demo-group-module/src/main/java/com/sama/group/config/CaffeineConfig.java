package com.sama.analytic.config;

import com.core4ct.api.system.OrgDubboService;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

/**
 * @Description: 缓存配置
 * @author: caihai
 * @date: 2024年07月15日 14:00
 */
@Configuration
public class CaffeineConfig {

    @DubboReference
    OrgDubboService orgDubboService;

    // 组织名称缓存
    @Bean(name = "orgNameCache")
    public Cache<String, String> orgNameCache() {
        return Caffeine.newBuilder()
                .expireAfterWrite(30, TimeUnit.MINUTES)
                .initialCapacity(100)
                .maximumSize(1000)
                .build();
    }

    // 虚机ip缓存
    @Bean(name = "vmIpCache")
    public Cache<Long, String> vmIpCache() {
        return Caffeine.newBuilder()
                .expireAfterWrite(30, TimeUnit.MINUTES)
                .initialCapacity(100)
                .maximumSize(1000)
                .build();
    }

    // 资产名称缓存
    @Bean(name = "assetNameCache")
    public Cache<Long, String> assetNameCache() {
        return Caffeine.newBuilder()
                .expireAfterWrite(30, TimeUnit.MINUTES)
                .initialCapacity(100)
                .maximumSize(5000)
                .build();
    }

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