package com.tensquare.ai.task;

import com.tensquare.ai.service.CnnService;
import com.tensquare.ai.service.Word2VecService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TrainTask {

    @Autowired
    private Word2VecService word2VecService;

    @Autowired
    private CnnService cnnService;

    @Scheduled(cron = "30 32 22 * * *")
    public void trainModel() {
        // 合并资料库
        word2VecService.mergeWord();
        // 构建词向量模型
        word2VecService.build();
        // 构建卷积模型
        cnnService.build();
    }

}
