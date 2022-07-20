package com.web.shopping.service;

import com.web.shopping.pojo.Brand;

import java.util.List;
import java.util.Map;

public interface BrandService {

    List<Brand> queryAllBrand();

    List<Brand> queryBrandOption();

    void addBrand(Brand brand);

    Brand queryBrandById(Long brandId);

    void updateBrand(Brand brand);

    void deleteBrandByIds(Long[] ids);
}
