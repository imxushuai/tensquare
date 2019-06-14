package com.tensquare.qa.dao;

import com.tensquare.qa.pojo.Problem;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * 数据访问接口
 * @author Administrator
 *
 */
public interface ProblemDao extends JpaRepository<Problem,String>,JpaSpecificationExecutor<Problem>{

    @Query("SELECT p FROM Problem p " +
            "WHERE id IN (SELECT pl.problemid FROM Pl pl WHERE pl.labelid = ?1) " +
            "ORDER BY p.replytime DESC")
    List<Problem> findNewProblemList(String labelId, Pageable pageable);

    @Query("SELECT p FROM Problem p " +
            "WHERE id IN (SELECT pl.problemid FROM Pl pl WHERE pl.labelid = ?1) " +
            "ORDER BY p.reply DESC")
    List<Problem> findHotProblemList(String labelId, Pageable pageable);

    @Query("SELECT p FROM Problem p " +
            "WHERE id IN (SELECT pl.problemid FROM Pl pl WHERE pl.labelid = ?1) " +
            "AND p.reply = 0 " +
            "ORDER BY p.createtime DESC")
    List<Problem> findWaitReplyProblemList(String labelId, Pageable pageable);
}
