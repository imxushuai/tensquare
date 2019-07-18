package com.tensquare.ai.service;

import com.tensquare.ai.util.CnnUtil;
import lombok.extern.slf4j.Slf4j;
import org.deeplearning4j.nn.graph.ComputationGraph;
import org.deeplearning4j.util.ModelSerializer;
import org.nd4j.linalg.dataset.api.iterator.DataSetIterator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import util.IkUtil;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Map;

/**
 * 智能分类模型
 */
@Slf4j
@Service
public class CnnService {

    @Value("${ai.cnnModel}")
    private String cnnModel;

    @Value("${ai.dataPath}")
    private String dataPath;

    @Value("${ai.vecModel}")
    private String vecModel;

    /**
     * 构建卷积模型
     */
    public void build() {
        try {
            // 创建计算图对象
            ComputationGraph computationGraph = CnnUtil.createComputationGraph(10);
            // 加载词向量，训练数据集
            String[] childPaths = {"ai", "db", "web"};
            DataSetIterator dataSetIterator = CnnUtil.getDataSetIterator(dataPath, childPaths, vecModel);
            // 训练
            computationGraph.fit(dataSetIterator);
            // 删除之前生成卷积模型
            Paths.get(cnnModel).toFile().delete();
            // 保存
            ModelSerializer.writeModel(computationGraph, cnnModel, true);
        } catch (IOException e) {
            log.error("卷积模型生成失败, e = ", e);
        }

    }

    /**
     * 返回map集合 分类与百分比
     * @param content
     * @return
     */
    public Map textClassify(String content) {
        log.info("待分类数据, content = [{}]", content);
        Map result = null;
        // 分词
        try {
            content = IkUtil.split(content, " ");
            String[] childPaths = {"ai", "db", "web"};
            // 获取预测结果
            result = CnnUtil.predictions(vecModel, cnnModel, dataPath, childPaths, content);
        } catch (IOException e) {
            log.error("智能分类失败, e = ", e);
        }
        return result;
    }


}
