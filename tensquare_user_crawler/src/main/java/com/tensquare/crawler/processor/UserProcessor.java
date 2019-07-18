package com.tensquare.crawler.processor;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

@Component
public class UserProcessor implements PageProcessor {

    @Override
    public void process(Page page) {
        // 添加爬取的页面
        page.addTargetRequests(page.getHtml().links().regex("https://blog.csdn.net/[a-z 0-9-]+/article/details/[0-9]{8}").all());
        // 昵称和头像
        String nickname = page.getHtml().xpath("//*[@id=\"uid\"]/text()").get();
        String image = page.getHtml().xpath("//*[@id=\"asideProfile\"]/div[1]/div[1]/a/img[1]").get();
        // 保存
        if (StringUtils.isNotBlank(nickname) && StringUtils.isNotBlank(image)) {
            page.putField("nickname", nickname);
            page.putField("image", image);
        } else {
            page.setSkip(true);
        }

    }

    @Override
    public Site getSite() {
        return Site.me().setRetryTimes(3000).setSleepTime(100);
    }
}
