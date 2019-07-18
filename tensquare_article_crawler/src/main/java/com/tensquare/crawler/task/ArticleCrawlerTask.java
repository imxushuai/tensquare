package com.tensquare.crawler.task;

import com.tensquare.crawler.pipeline.ArticlePipeline;
import com.tensquare.crawler.pipeline.ArticleTxtPipeline;
import com.tensquare.crawler.processor.ArticleProcessor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.scheduler.RedisScheduler;

@Slf4j
@Component
public class ArticleCrawlerTask {

    @Autowired
    private ArticlePipeline articlePipeline;

    @Autowired
    private ArticleTxtPipeline articleTxtPipeline;

    @Autowired
    private RedisScheduler redisScheduler;

    @Autowired
    private ArticleProcessor articleProcessor;

    @Scheduled(cron = "0 9 2 * * *")
    public void aiTask() {
        log.info("开始爬取AI文章");
        articlePipeline.setChannelId("ai");
        articleTxtPipeline.setChannelId("ai");
        Spider spider = Spider.create(articleProcessor);
        spider.addUrl("https://blog.csdn.net/nav/ai/")
                .addPipeline(articlePipeline)
                .addPipeline(articleTxtPipeline)
                .setScheduler(redisScheduler)
                .start();
    }

    @Scheduled(cron = "0 2 2 * * *")
    public void blockChainTask() {
        log.info("开始爬取区块链文章");
        articlePipeline.setChannelId("blockchain");
        articleTxtPipeline.setChannelId("blockchain");
        Spider spider = Spider.create(articleProcessor);
        spider.addUrl("https://blog.csdn.net/nav/blockchain/")
                .addPipeline(articlePipeline)
                .addPipeline(articleTxtPipeline)
                .setScheduler(redisScheduler)
                .run();
    }

    @Scheduled(cron = "0 2 2 * * *")
    public void dbTask() {
        log.info("开始爬取区数据库文章");
        articlePipeline.setChannelId("db");
        articleTxtPipeline.setChannelId("db");
        Spider spider = Spider.create(articleProcessor);
        spider.addUrl("https://blog.csdn.net/nav/db/")
                .addPipeline(articlePipeline)
                .addPipeline(articleTxtPipeline)
                .setScheduler(redisScheduler)
                .run();
    }

    @Scheduled(cron = "0 0 3 * * *")
    public void langTask() {
        log.info("开始爬取编程语言文章");
        articlePipeline.setChannelId("lang");
        articleTxtPipeline.setChannelId("lang");
        Spider spider = Spider.create(articleProcessor);
        spider.addUrl("https://blog.csdn.net/nav/lang/")
                .addPipeline(articlePipeline)
                .addPipeline(articleTxtPipeline)
                .setScheduler(redisScheduler)
                .run();
    }

    @Scheduled(cron = "0 0 4 * * *")
    public void newsTask() {
        log.info("开始爬取资讯文章");
        articlePipeline.setChannelId("news");
        articleTxtPipeline.setChannelId("news");
        Spider spider = Spider.create(articleProcessor);
        spider.addUrl("https://blog.csdn.net/nav/news/")
                .addPipeline(articlePipeline)
                .addPipeline(articleTxtPipeline)
                .setScheduler(redisScheduler)
                .run();
    }

    @Scheduled(cron = "0 0 5 * * *")
    public void webTask() {
        log.info("开始爬取前端文章");
        articlePipeline.setChannelId("web");
        articleTxtPipeline.setChannelId("web");
        Spider spider = Spider.create(articleProcessor);
        spider.addUrl("https://blog.csdn.net/nav/web/")
                .addPipeline(articlePipeline)
                .addPipeline(articleTxtPipeline)
                .setScheduler(redisScheduler)
                .run();
    }


}
