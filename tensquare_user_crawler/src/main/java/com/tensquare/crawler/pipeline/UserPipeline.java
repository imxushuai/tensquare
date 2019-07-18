package com.tensquare.crawler.pipeline;

import com.tensquare.crawler.dao.UserDao;
import com.tensquare.crawler.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;
import util.DownloadUtil;
import util.IdWorker;

import java.io.IOException;

@Slf4j
@Component
public class UserPipeline implements Pipeline {


    @Autowired
    private UserDao userDao;

    @Autowired
    private IdWorker idWorker;

    @Override
    public void process(ResultItems resultItems, Task task) {
        // 取出nickname和image
        String nickname = resultItems.get("nickname").toString();
        String image = resultItems.get("image").toString();

        User user = new User();
        user.setId(idWorker.nextId().toString());
        user.setNickname(nickname);
        String fileName = image.substring(image.lastIndexOf("/") + 1, image.lastIndexOf(" ") - 1);
        user.setAvatar(fileName);
        userDao.save(user);

        // 下载图片
        try {
            String url = image.substring(image.indexOf("https://"), image.lastIndexOf(" ") - 1);
            DownloadUtil.download(url, fileName, "E:/userImage");
        } catch (IOException e) {
            log.error("下载文件发生异常！e = ", e);
        }

    }
}
