package com.tensquare.spit.repository;

import com.tensquare.spit.pojo.Spit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SpitRepository extends MongoRepository<Spit, String> {

    Page<Spit> findByParentid(String parentId, Pageable pageable);
}
