package com.web.shopping.controller;

import com.web.shopping.pojo.ContentCategory;
import com.web.shopping.service.ContentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ContentCategoryController {

    @Autowired
    private ContentCategoryService contentCategoryService;

    @GetMapping("/queryAllCategoryList")
    public List<ContentCategory> queryAllCategoryList(){
        return contentCategoryService.queryAllCategory();
    }
}
