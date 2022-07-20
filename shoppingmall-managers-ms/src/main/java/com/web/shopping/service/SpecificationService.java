package com.web.shopping.service;

import com.web.shopping.pojo.Specification;

import java.util.List;


public interface SpecificationService {

    List<Specification> queryAllSpecification();

    List<Specification> querySpecificationByCondition(Specification specification);

    void addSpecification(Specification specification);

    void updateSpecification(Specification specification);

    Specification querySpecificationById(Long sp_id);

    void deleteSpecificationByIds(Long[] ids);
}
