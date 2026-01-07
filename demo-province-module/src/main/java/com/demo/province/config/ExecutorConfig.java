package com.sama.ledger.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * 线程池配置
 * @author: huxh
 * @description:
 * @datetime: 2025/7/4 16:53
 */
@Configuration
public class ExecutorConfig {

    @Bean(name = "bypass")
    public ThreadPoolTaskExecutor initTaskExecutor() {
        ThreadPoolTaskExecutor asyncExecutor = new ThreadPoolTaskExecutor();
        //核心线程数
        asyncExecutor.setCorePoolSize(1);
        //线程池维护线程的最大数量,只有在缓冲队列满了之后才会申请超过核心线程数的线程
        asyncExecutor.setMaxPoolSize(10);
        //缓存队列
        asyncExecutor.setQueueCapacity(0);
        //允许的空闲时间,当超过了核心线程数之外的线程在空闲时间到达之后会被销毁
        asyncExecutor.setKeepAliveSeconds(500);
        //异步方法内部线程名称
        asyncExecutor.setThreadNamePrefix("bypass-");
        /**
         * 当线程池的任务缓存队列已满并且线程池中的线程数目达到maximumPoolSize，如果还有任务到来就会采取任务拒绝策略
         * 通常有以下四种策略：
         * ThreadPoolExecutor.AbortPolicy:丢弃任务并抛出RejectedExecutionException异常。
         * ThreadPoolExecutor.DiscardPolicy：也是丢弃任务，但是不抛出异常。
         * ThreadPoolExecutor.DiscardOldestPolicy：丢弃队列最前面的任务，然后重新尝试执行任务（重复此过程）
         * ThreadPoolExecutor.CallerRunsPolicy：重试添加当前的任务，自动重复调用 execute() 方法，直到成功
         */
        asyncExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardPolicy());
        // 线程池关闭的时候等待所有任务都完成后，再继续销毁其他的 Bean，这样这些异步任务的销毁就会先于数据库连接池对象的销毁
        asyncExecutor.setWaitForTasksToCompleteOnShutdown(true);
        asyncExecutor.initialize();

        return asyncExecutor;
    }

    @Bean(name = "comprehensiveProtectionEngine")
    public ThreadPoolTaskExecutor comprehensiveProtectionEngineExecutor() {
        ThreadPoolTaskExecutor asyncExecutor = new ThreadPoolTaskExecutor();
        asyncExecutor.setCorePoolSize(20);
        asyncExecutor.setMaxPoolSize(50);
        asyncExecutor.setQueueCapacity(200);
        asyncExecutor.setKeepAliveSeconds(500);
        asyncExecutor.setThreadNamePrefix("comprehensiveProtectionEngine-");
        asyncExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());
        asyncExecutor.setWaitForTasksToCompleteOnShutdown(true);
        asyncExecutor.initialize();

        return asyncExecutor;
    }

    @Bean(name = "benefitEngine")
    public ThreadPoolTaskExecutor benefitEngineExecutor() {
        ThreadPoolTaskExecutor asyncExecutor = new ThreadPoolTaskExecutor();
        asyncExecutor.setCorePoolSize(20);
        asyncExecutor.setMaxPoolSize(50);
        asyncExecutor.setQueueCapacity(200);
        asyncExecutor.setKeepAliveSeconds(500);
        asyncExecutor.setThreadNamePrefix("benefitEngine-");
        asyncExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());
        asyncExecutor.setWaitForTasksToCompleteOnShutdown(true);
        asyncExecutor.initialize();

        return asyncExecutor;
    }

    @Bean(name = "efficiencyEngine")
    public ThreadPoolTaskExecutor efficiencyEngineExecutor() {
        ThreadPoolTaskExecutor asyncExecutor = new ThreadPoolTaskExecutor();
        asyncExecutor.setCorePoolSize(20);
        asyncExecutor.setMaxPoolSize(50);
        asyncExecutor.setQueueCapacity(200);
        asyncExecutor.setKeepAliveSeconds(500);
        asyncExecutor.setThreadNamePrefix("efficiencyEngine-");
        asyncExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());
        asyncExecutor.setWaitForTasksToCompleteOnShutdown(true);
        asyncExecutor.initialize();

        return asyncExecutor;
    }

    @Bean(name = "metricSecondaryProcess")
    public ThreadPoolTaskExecutor metricSecondaryProcessExecutor() {
        ThreadPoolTaskExecutor asyncExecutor = new ThreadPoolTaskExecutor();
        asyncExecutor.setCorePoolSize(20);
        asyncExecutor.setMaxPoolSize(50);
        asyncExecutor.setQueueCapacity(200);
        asyncExecutor.setKeepAliveSeconds(500);
        asyncExecutor.setThreadNamePrefix("metricSecondaryProcess-");
        asyncExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());
        asyncExecutor.setWaitForTasksToCompleteOnShutdown(true);
        asyncExecutor.initialize();

        return asyncExecutor;
    }

}
