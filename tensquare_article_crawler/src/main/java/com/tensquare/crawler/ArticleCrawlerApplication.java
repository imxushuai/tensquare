package com.tensquare.crawler;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import us.codecraft.webmagic.scheduler.RedisScheduler;
import util.IdWorker;

@EnableScheduling
@SpringBootApplication
public class ArticleCrawlerApplication {

    @Value("${spring.redis.host}")
    private String REDIS_HOST;

    public static void main(String[] args) {
        SpringApplication.run(ArticleCrawlerApplication.class, args);
    }

    @Bean
    public IdWorker idWorker() {
        return new IdWorker(1, 11);
    }

    @Bean
    public RedisScheduler redisScheduler() {
        return new RedisScheduler(REDIS_HOST);
    }
}
