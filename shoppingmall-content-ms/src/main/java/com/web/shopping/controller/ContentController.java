package com.web.shopping.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.web.shopping.pojo.Content;
import com.web.shopping.pojo.PageResult;
import com.web.shopping.pojo.ResultBean;
import com.web.shopping.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ContentController {

    @Autowired
    private ContentService contentService;

    @GetMapping("/queryContentByPage/{pageNum}/{pageSize}")
    public PageResult queryContentByPage(@PathVariable("pageNum") int pageNum,
                                         @PathVariable("pageSize") int pageSize){

        PageHelper.startPage(pageNum, pageSize);
        Page<Content> page = (Page<Content>) contentService.queryAllContent();
        PageResult pageResult = new PageResult();
        pageResult.setRows(page.getResult());
        pageResult.setTotal(page.getTotal());

        return pageResult;
    }

    @GetMapping("/queryContentById/{content_id}")
    public Content queryContentById(@PathVariable("content_id") Long ContentId){

        return contentService.queryContentById(ContentId);
    }

    @PostMapping("/addContent")
    public ResultBean addContent(@RequestBody Content content){
        try {
            System.out.println("添加广告标题："+content.getTitle());
            System.out.println("添加广告状态："+content.getStatus());
            contentService.addContent(content);
            return ResultBean.ok("添加成功");
        } catch (Exception e) {
            e.printStackTrace();
            return ResultBean.error("添加失败！");
        }
    }

    @PostMapping("/updateContent")
    public ResultBean updateContent(@RequestBody Content content){
        try {
            System.out.println("修改广告标题："+content.getTitle());
            System.out.println("修改广告状态："+content.getStatus());
            contentService.updateContent(content);
            return ResultBean.ok("修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            return ResultBean.error("修改失败！");
        }
    }

    @PostMapping("/deleteContentByIds/{ids}")
    public ResultBean deleteContentByIds(@PathVariable("ids") Long[] ids){
        try {
            System.out.println("删除广告Id：");
            for (Long id : ids) {
                System.out.print(id + "\t");
            }
            contentService.deleteContentByIds(ids);
            return ResultBean.ok("删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return ResultBean.error("删除失败");
        }
    }

    @GetMapping("/findContentByCategoryId/{categoryId}")
    public List<Content> findContentByCategoryId(@PathVariable Long categoryId){

        System.out.println("查询广告Id："+categoryId);
        return contentService.queryContentByCategoryId(categoryId);
    }
}
