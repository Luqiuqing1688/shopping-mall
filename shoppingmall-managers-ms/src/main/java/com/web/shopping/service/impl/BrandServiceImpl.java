package com.web.shopping.service.impl;

import com.web.shopping.mapper.BrandMapper;
import com.web.shopping.pojo.Brand;
import com.web.shopping.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandMapper brandMapper;

    @Override
    public List<Brand> queryAllBrand() {
        return brandMapper.selectByExample(null);
    }

    @Override
    public List<Brand> queryBrandOption() {
        return brandMapper.selectBrandOptionList();
    }

    @Override
    public void addBrand(Brand brand) {
        brandMapper.insert(brand);
    }

    @Override
    public Brand queryBrandById(Long brandId) {
        return brandMapper.selectByPrimaryKey(brandId);
    }

    @Override
    public void updateBrand(Brand brand) {
        brandMapper.updateByPrimaryKey(brand);
    }

    @Override
    public void deleteBrandByIds(Long[] ids) {
        for (Long id : ids) {
            brandMapper.deleteByPrimaryKey(id);
        }
    }


}
