package com.tensquare.ai.service;

import lombok.extern.slf4j.Slf4j;
import org.deeplearning4j.models.embeddings.loader.WordVectorSerializer;
import org.deeplearning4j.models.word2vec.Word2Vec;
import org.deeplearning4j.text.sentenceiterator.LineSentenceIterator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import util.FileUtil;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

@Slf4j
@Service
public class Word2VecService {

    @Value("${ai.wordLib}")
    private String wordLib;

    @Value("${ai.dataPath}")
    private String dataPath;

    @Value("${ai.vecModel}")
    private String vecModel;

    /**
     * 文件合并
     */
    public void mergeWord() {
        List<String> fileNames = FileUtil.getFiles(dataPath);
        try {
            FileUtil.merge(wordLib, fileNames);
        } catch (IOException e) {
            log.error("合并分词资料库失败，e = ", e);
        }
    }

    /**
     * 构建词向量模型
     */
    public void build() {
        try {
            // 加载分词库
            LineSentenceIterator sentenceIterator = new LineSentenceIterator(new File(wordLib));

            Word2Vec vec = new Word2Vec.Builder()
                    .minWordFrequency(5)// 一个词在语料中最少出现的次数，若低于该值将不予学习
                    .iterations(1)// 处理数据时允许系数更新的次数
                    .layerSize(100)// 指定词向量中特征数量
                    .seed(42)// 随机数发生器
                    .windowSize(5)// 当前词与预测词在句中最大距离
                    .iterate(sentenceIterator)// 指定数据集
                    .build();
            // 构建
            vec.fit();
            // 删除原有模型
            Paths.get(vecModel).toFile().delete();
            // 保存新生成的模型
            WordVectorSerializer.writeWordVectors(vec, vecModel);
        } catch (Exception e) {
            log.error("词向量模型构建失败, e = ", e);
        }

    }
}
