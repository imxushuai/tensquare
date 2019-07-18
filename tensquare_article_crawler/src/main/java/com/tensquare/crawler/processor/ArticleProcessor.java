package com.tensquare.crawler.processor;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * 文章爬取类
 */
@Component
public class ArticleProcessor implements PageProcessor {

    @Override
    public void process(Page page) {
        // 添加爬取的页面
        page.addTargetRequests(page.getHtml()
                .links().regex("https://blog.csdn.net/[a-z 0-9-]+/article/details/[0-9]{8}").all());
        // 获取标题以及内容
        String title = page.getHtml().xpath("//*[@id=\"mainBox\"]/main/div[1]/div/div/div[1]/h1/text()").get();
        String content = page.getHtml().xpath("//*[@id=\"article_content\"]").get();
        if (StringUtils.isNotBlank(title) && StringUtils.isNotBlank(content)) {
            page.putField("title", title);
            page.putField("content", content);
        } else {
            page.setSkip(true);
        }
    }

    @Override
    public Site getSite() {
        return Site.me().setRetryTimes(100).setSleepTime(100);
    }
}
