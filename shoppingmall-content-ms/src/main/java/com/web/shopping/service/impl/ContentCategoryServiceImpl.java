package com.web.shopping.service.impl;

import com.web.shopping.mapper.ContentCategoryMapper;
import com.web.shopping.pojo.ContentCategory;
import com.web.shopping.service.ContentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {

    @Autowired
    private ContentCategoryMapper contentCategoryMapper;

    @Override
    public List<ContentCategory> queryAllCategory() {
        return contentCategoryMapper.selectByExample(null);
    }
}
