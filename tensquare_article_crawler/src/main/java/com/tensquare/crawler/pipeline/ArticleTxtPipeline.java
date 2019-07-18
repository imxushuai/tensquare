package com.tensquare.crawler.pipeline;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;
import util.HTMLUtil;
import util.IkUtil;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.UUID;

@Slf4j
@Component
public class ArticleTxtPipeline implements Pipeline {

    @Value("${ai.dataPath}")
    private String dataPath;

    private String channelId;

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    @Override
    public void process(ResultItems resultItems, Task task) {
        String title = resultItems.get("title");
        String content = HTMLUtil.delHTMLTag(resultItems.get("content"));
        log.info("文章名 [{}]", title);
        // 输出到文本文件
        try {
            // 输出流
            PrintWriter printWriter = new PrintWriter(new File(dataPath + "/" + channelId + "/" + UUID.randomUUID() + ".txt"));
            printWriter.print(IkUtil.split(content, " "));
            printWriter.close();
        } catch (IOException e) {
            log.error("持久化网页内如至文件失败, e = ", e);
        }
    }
}
