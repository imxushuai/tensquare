package com.tensquare.crawler.pipeline;

import com.tensquare.crawler.dao.ArticleDao;
import com.tensquare.crawler.pojo.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;
import util.IdWorker;

@Component
public class ArticlePipeline implements Pipeline {

    @Autowired
    private ArticleDao articleDao;

    @Autowired
    private IdWorker idWorker;

    private String channelId;

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    @Override
    public void process(ResultItems resultItems, Task task) {
        // 取出爬取类中的title和content
        String title = resultItems.get("title");
        String content = resultItems.get("content");
        // 构造文章对象
        Article article = new Article();
        article.setChannelid(channelId);
        article.setId(idWorker.nextId().toString());
        article.setTitle(title);
        article.setContent(content);
        // 保存
        articleDao.save(article);
    }
}
