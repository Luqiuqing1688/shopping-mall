package com.web.shopping.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.web.shopping.pojo.PageResult;
import com.web.shopping.pojo.TypeTemplate;
import com.web.shopping.service.TypeTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TypeTemplateController {

    @Autowired
    private TypeTemplateService typeTemplateService;

    @GetMapping("/findAllTypeTemplate")
    public List<TypeTemplate> findAllTypeTemplate(){
        return typeTemplateService.queryAllTypeTemplate();
    }

    @GetMapping("/findTypeTemplateByPage/{pageNum}/{pageSize}")
    public PageResult findTypeTemplateByPage(@PathVariable("pageNum") int pageNum,
                                             @PathVariable("pageSize") int pageSize){
        
        PageHelper.startPage(pageNum, pageSize);
        Page<TypeTemplate> page = (Page<TypeTemplate>) typeTemplateService.queryAllTypeTemplate();

        PageResult pageResult = new PageResult();
        pageResult.setRows(page.getResult());
        pageResult.setTotal(page.getTotal());

        return pageResult;
    }
}
