package com.web.shopping.service.impl;

import com.web.shopping.mapper.SpecificationMapper;
import com.web.shopping.mapper.SpecificationOptionMapper;
import com.web.shopping.pojo.Specification;
import com.web.shopping.pojo.SpecificationExample;
import com.web.shopping.pojo.SpecificationOption;
import com.web.shopping.pojo.SpecificationOptionExample;
import com.web.shopping.service.SpecificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpecificationServiceImpl implements SpecificationService {

    @Autowired
    private SpecificationMapper specificationMapper;
    @Autowired
    private SpecificationOptionMapper specificationOptionMapper;

    @Override
    public List<Specification> queryAllSpecification() {
        return specificationMapper.selectByExample(null);
    }

    @Override
    public List<Specification> querySpecificationByCondition(Specification specification) {

        SpecificationExample example = new SpecificationExample();
        SpecificationExample.Criteria criteria = example.createCriteria();
        //模糊查询规格名
        if (specification.getSpecName() != null && !specification.getSpecName().equals("")) {

            criteria.andSpecNameLike(specification.getSpecName());
        }

        return specificationMapper.selectByExample(example);
    }

    @Override
    public void addSpecification(Specification specification) {
        specificationMapper.insert(specification);
        //将规格选项同步到数据库中
        for (SpecificationOption option : specification.getSpecificationOptionList()) {
            //specification设置了主键回填
            option.setSpecId(specification.getId());
            specificationOptionMapper.insert(option);
        }
    }

    @Override
    public void updateSpecification(Specification specification) {
        //修改规则信息
        specificationMapper.updateByPrimaryKey(specification);
        //先删除规格选项，再添加
        SpecificationOptionExample example = new SpecificationOptionExample();
        SpecificationOptionExample.Criteria criteria = example.createCriteria();
        criteria.andSpecIdEqualTo(specification.getId());
        specificationOptionMapper.deleteByExample(example);
        //添加规格选项
        for (SpecificationOption option : specification.getSpecificationOptionList()) {
            //设置spId
            option.setSpecId(specification.getId());
            specificationOptionMapper.insert(option);
        }
    }

    @Override
    public Specification querySpecificationById(Long sp_id) {
        Specification specification = specificationMapper.selectByPrimaryKey(sp_id);
        //查询规格对应Id
        SpecificationOptionExample example = new SpecificationOptionExample();
        SpecificationOptionExample.Criteria criteria = example.createCriteria();
        criteria.andSpecIdEqualTo(sp_id);
        List<SpecificationOption> specificationOptions = specificationOptionMapper.selectByExample(example);
        //将查询的规格选项设置到规格中
        specification.setSpecificationOptionList(specificationOptions);

        return specification;
    }

    @Override
    public void deleteSpecificationByIds(Long[] ids) {

        for (Long id : ids) {
            //先删除规格选项
            SpecificationOptionExample example = new SpecificationOptionExample();
            SpecificationOptionExample.Criteria criteria = example.createCriteria();
            System.out.println("删除Id："+id);
            criteria.andSpecIdEqualTo(id);
            specificationOptionMapper.deleteByExample(example);
        }
        //删除规格信息
        for (Long id : ids) {
            specificationMapper.deleteByPrimaryKey(id);
        }
    }
}
