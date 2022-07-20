package com.web.shopping.service.impl;

import com.web.shopping.mapper.TypeTemplateMapper;
import com.web.shopping.pojo.TypeTemplate;
import com.web.shopping.service.TypeTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeTemplateServiceImpl implements TypeTemplateService {

    @Autowired
    private TypeTemplateMapper typeTemplateMapper;

    @Override
    public List<TypeTemplate> queryAllTypeTemplate() {
        return typeTemplateMapper.selectByExample(null);
    }
}
