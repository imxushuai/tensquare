package com.tensquare.spit.service;

import com.tensquare.spit.pojo.Spit;
import com.tensquare.spit.repository.SpitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import util.IdWorker;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class SpitService {

    @Autowired
    private SpitRepository spitRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private IdWorker idWorker;

    /**
     * 查询全部记录
     *
     * @return
     */
    public List<Spit> findAll() {
        return spitRepository.findAll();
    }

    /**
     * 根据主键查询实体
     *
     * @param id
     * @return
     */
    public Spit findById(String id) {
        Spit spit = spitRepository.findById(id).get();
        return spit;
    }

    /**
     * 发布吐槽（或吐槽评论）
     *
     * @param spit
     */
    public void add(Spit spit) {
        spit.set_id(idWorker.nextId() + "");
        spit.setPublishtime(new Date());//发布日期
        spit.setVisits(0);//浏览量
        spit.setShare(0);//分享数
        spit.setThumbup(0);//点赞数
        spit.setComment(0);//回复数
        spit.setState("1");//状态
        if (spit.getParentid() != null && !"".equals(spit.getParentid())) {//
            // 如果存在上级ID,评论
            Query query = new Query();
            query.addCriteria(Criteria.where("_id").is(spit.getParentid()));
            Update update = new Update();
            update.inc("comment", 1);
            mongoTemplate.updateFirst(query, update, "spit");
        }
        spitRepository.save(spit);
    }

    /**
     * 修改
     *
     * @param spit
     */
    public void update(Spit spit) {
        spitRepository.save(spit);
    }

    /**
     * 删除
     *
     * @param id
     */
    public void deleteById(String id) {
        spitRepository.deleteById(id);
    }

    /**
     * 根据上级ID查询吐槽分页数据
     *
     * @param parentId 上级ID
     * @param page     当前页码
     * @param size     当前页记录数
     * @return
     */
    public Page<Spit> findByParentid(String parentId, int page, int size) {
        return spitRepository.findByParentid(parentId, PageRequest.of(page, size));
    }

    /**
     * 点赞
     *
     * @param id 吐槽ID
     */
    public void updateThumbup(String id) {
        Optional<Spit> optionalSpit = spitRepository.findById(id);
        if (optionalSpit.isPresent()) {
            Spit spit = optionalSpit.get();
            spit.setThumbup(spit.getThumbup() == null ? 0 : spit.getThumbup() + 1);
        }
    }
}
