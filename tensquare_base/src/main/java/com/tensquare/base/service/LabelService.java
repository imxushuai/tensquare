package com.tensquare.base.service;

import com.tensquare.base.dao.LabelRepository;
import com.tensquare.base.entity.Label;
import entity.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import util.IdWorker;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class LabelService {

    @Autowired
    private LabelRepository labelRepository;

    @Autowired
    private IdWorker idWorker;

    public Label save(Label label) {
        label.setId(idWorker.nextId().toString());
        return labelRepository.save(label);
    }

    public List<Label> findAll() {
        return labelRepository.findAll();
    }

    public Label findById(String id) {
        return labelRepository.findById(id).get();
    }

    public void deleteById(String id) {
        labelRepository.deleteById(id);
    }

    public void update(Label label) {
        labelRepository.save(label);
    }

    /**
     * 分页+条件
     */
    public PageResult<Label> queryByPage(Map searchMap, int page, int size) {
        Specification<Label> specification = createSpecification(searchMap);
        Page<Label> labelPage = labelRepository.findAll(specification, PageRequest.of(page, size));
        return new PageResult<>(labelPage.getTotalElements(), labelPage.getContent());
    }

    public List<Label> findAll(Map searchMap) {
        Specification<Label> specification = createSpecification(searchMap);
        return labelRepository.findAll(specification);
    }

    /**
     * 构建查询条件
     *
     * @param searchMap 查询条件
     * @return Specification 查询规格
     */
    private Specification<Label> createSpecification(Map searchMap) {
        String labelname = (String) searchMap.get("labelname");
        String state = (String) searchMap.get("state");
        String recommend = (String) searchMap.get("recommend");
        return (Specification<Label>) (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicateList = new ArrayList<>();

            if (!StringUtils.isEmpty(labelname)) {
                predicateList.add(criteriaBuilder.like(root.get("labelname").as(String.class), "%" + labelname + "%"));
            }
            if (!StringUtils.isEmpty(state)) {
                predicateList.add(criteriaBuilder.equal(root.get("state").as(String.class),  state));
            }
            if (!StringUtils.isEmpty(recommend)) {
                predicateList.add(criteriaBuilder.equal(root.get("recommend").as(String.class), "%" + recommend + "%"));
            }
            return criteriaBuilder.and(predicateList.toArray(new Predicate[predicateList.size()]));
        };
    }
}
