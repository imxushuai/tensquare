package com.tensquare.crawler.task;

import com.tensquare.crawler.pipeline.UserPipeline;
import com.tensquare.crawler.processor.UserProcessor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.scheduler.RedisScheduler;

/**
 * 用户数据爬取类
 */
@Slf4j
@Component
public class UserCrawlerTask {

    @Autowired
    private UserProcessor userProcessor;

    @Autowired
    private UserPipeline userPipeline;

    @Autowired
    private RedisScheduler redisScheduler;

    @Scheduled(cron = "0 8 15 * * *")
    public void userTask () {
        log.info("开始爬取用户数据");
        Spider spider = Spider.create(userProcessor);
        spider.addUrl("https://blog.csdn.net/")
                .addPipeline(userPipeline)
                .setScheduler(redisScheduler)
                .start();
    }

}
