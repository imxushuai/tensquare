package com.tensquare.search.dao;

import com.tensquare.search.pojo.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
* 文章数据访问层接口
*/
public interface ArticleSearchDao extends ElasticsearchRepository<Article,String> {
    Page<Article> findByTitleOrContentLike(String title, String content, Pageable pageable);
}
