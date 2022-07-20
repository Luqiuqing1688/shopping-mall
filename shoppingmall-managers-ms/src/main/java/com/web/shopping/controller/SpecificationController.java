package com.web.shopping.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.web.shopping.pojo.PageResult;
import com.web.shopping.pojo.ResultBean;
import com.web.shopping.pojo.Specification;
import com.web.shopping.service.SpecificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SpecificationController {

    @Autowired
    private SpecificationService specificationService;

    /**
     * 查询所有规格
     *
     * @return
     */
    @GetMapping("/findAllSpecification")
    public List<Specification> findAllSpecification() {
        return specificationService.queryAllSpecification();
    }

    /**
     * 模糊查询所有规格
     *
     * @param specification
     * @return
     */
    @GetMapping("/findSpecificationByCondition")
    public List<Specification> findSpecificationByCondition(@RequestBody Specification specification) {
        System.out.println("查询规格名：" + specification.getSpecName());
        return specificationService.querySpecificationByCondition(specification);
    }

    /**
     * 分页查询所有规格
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/querySpecificationByPage")
    public PageResult querySpecificationByPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        Page<Specification> page = (Page<Specification>) specificationService.queryAllSpecification();

        PageResult pageResult = new PageResult();
        pageResult.setRows(page.getResult());
        pageResult.setTotal(page.getTotal());

        return pageResult;
    }

    /**
     * 添加规格和规格选项
     *
     * @param specification
     * @return
     */
    @PostMapping("/addSpecification")
    public ResultBean addSpecification(@RequestBody Specification specification) {
        try {
            System.out.println("specificationId:" + specification.getId());
            System.out.println("specificationName:" + specification.getSpecName());
            specificationService.addSpecification(specification);
            return ResultBean.ok("添加成功!");
        } catch (Exception e) {
            e.printStackTrace();
            return ResultBean.error("添加失败！");
        }
    }

    @PostMapping("/updateSpecification")
    public ResultBean updateSpecification(@RequestBody Specification specification) {
        try {
            System.out.println("specificationId:" + specification.getId());
            System.out.println("specificationName:" + specification.getSpecName());
            specificationService.updateSpecification(specification);
            return ResultBean.ok("修改成功!");
        } catch (Exception e) {
            e.printStackTrace();
            return ResultBean.error("修改失败！");
        }
    }

    @GetMapping("/findSpecificationById/{sp_id}")
    public Specification findSpecificationById(@PathVariable("sp_id") Long sp_id) {
        return specificationService.querySpecificationById(sp_id);
    }

    @GetMapping("/deleteSpecificationByIds/{ids}")
    public ResultBean deleteSpecificationByIds(@PathVariable("ids") Long[] ids) {
        try {
            System.out.println("删除规格Id：");
            for (Long id : ids) {
                System.out.print(id+"\t");
            }
            System.out.println();
            specificationService.deleteSpecificationByIds(ids);
            return ResultBean.ok("删除成功!");
        } catch (Exception e) {
            e.printStackTrace();
            return ResultBean.error("删除失败！");
        }
    }
}
