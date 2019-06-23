package com.tensquare.article.service;

import com.tensquare.article.dao.CommentDao;
import com.tensquare.article.pojo.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import util.IdWorker;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentDao commentDao;

    @Autowired
    private IdWorker idWorker;

    public void add(Comment comment) {
        comment.setId(idWorker.nextId() + "");
        commentDao.save(comment);
    }

    /**
     * 查询指定文章评论
     *
     * @param articleid 文章ID
     * @return 评论
     */
    public List<Comment> findByArticleid(String articleid){
        return commentDao.findByArticleid(articleid);
    }

    public void deleteById(String commentId) {
        commentDao.deleteById(commentId);
    }
}
