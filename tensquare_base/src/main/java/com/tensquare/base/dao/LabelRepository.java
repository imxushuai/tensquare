package com.tensquare.base.dao;

import com.tensquare.base.entity.Label;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface LabelRepository extends JpaRepository<Label, String>, JpaSpecificationExecutor<Label> {
}
