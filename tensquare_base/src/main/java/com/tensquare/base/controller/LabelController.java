package com.tensquare.base.controller;

import com.tensquare.base.entity.Label;
import com.tensquare.base.service.LabelService;
import entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("label")
public class LabelController {

    @Autowired
    private LabelService labelService;

    @PostMapping
    public Result insert(@RequestBody Label label) {
        labelService.save(label);
        return Result.ok("新增成功");
    }

    @GetMapping
    public Result queryLabels() {
        List<Label> labelList = labelService.findAll();
        return Result.ok(labelList);
    }

    @GetMapping("{labelId}")
    public Result queryById(@PathVariable("labelId") String id) {
        return Result.ok(labelService.findById(id));
    }

    @DeleteMapping("{labelId}")
    public Result remove(@PathVariable("labelId") String id) {
        labelService.deleteById(id);
        return Result.ok("删除成功");
    }

    @PutMapping("{labelId}")
    public Result update(@RequestBody Label label,
                         @PathVariable("labelId") String id) {
        label.setId(id);
        labelService.update(label);
        return Result.ok("修改成功");
    }

    @PostMapping("/search/{page}/{size}")
    public Result queryByPage(@RequestBody Map searchMap,
                              @PathVariable("page") int page,
                              @PathVariable("size") int size) {
        return Result.ok(labelService.queryByPage(searchMap, page, size));
    }


}
