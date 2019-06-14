package com.tensquare.article.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.tensquare.article.pojo.Article;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * 数据访问接口
 * @author Administrator
 *
 */
public interface ArticleDao extends JpaRepository<Article,String>,JpaSpecificationExecutor<Article>{

    @Modifying
    @Query("UPDATE Article a SET a.state = '1' " +
            "WHERE a.id = ?1")
    void updateStateById(Integer articleId);

        @Modifying
        @Query("UPDATE Article a SET a.thumbup = a.thumbup + 1 " +
                "WHERE a.id = ?1")
        void updateThumbupById(Integer articleId);
}
