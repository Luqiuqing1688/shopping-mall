package com.web.shopping.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.web.shopping.pojo.Brand;
import com.web.shopping.pojo.PageResult;
import com.web.shopping.pojo.ResultBean;
import com.web.shopping.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.xml.transform.Result;
import java.util.List;
import java.util.Map;

@RestController
public class BrandController {

    @Autowired
    private BrandService brandService;

    @GetMapping("/findAllBrand")
    public List<Brand> findAllBrand() {
        return brandService.queryAllBrand();
    }

    @GetMapping("/findBrandByPage/{pageNum}/{pageSize}")
    public PageResult findBrandByPage(@PathVariable int pageNum,
                                      @PathVariable int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        Page<Brand> page = (Page<Brand>) brandService.queryAllBrand();

        PageResult pageResult = new PageResult();
        pageResult.setRows(page.getResult());
        pageResult.setTotal(page.getTotal());
        return pageResult;
    }

    @GetMapping("/findBrandOption")
    public List<Brand> findBrandOption() {
        return brandService.queryBrandOption();
    }

    @PostMapping("/addBrand")
    public ResultBean addBrand(@RequestBody Brand brand) {
        try {
            System.out.println("添加的品牌名：" + brand.getName());
            brandService.addBrand(brand);
            return ResultBean.ok("添加成功！");
        } catch (Exception e) {
            e.printStackTrace();
            return ResultBean.error("添加失败");
        }
    }

    @GetMapping("/queryBrandById/{brandId}")
    public Brand queryBrandById(@PathVariable Long brandId) {
        System.out.println("查询Id：" + brandId);
        return brandService.queryBrandById(brandId);
    }

    @PostMapping("/updateBrand")
    public ResultBean updateBrand(@RequestBody Brand brand) {
        try {
            System.out.println("修改的品牌名：" + brand.getName());
            brandService.updateBrand(brand);
            return ResultBean.ok("修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            return ResultBean.error("修改失败");
        }
    }

    @GetMapping("/deleteBrandByIds/{ids}")
    public ResultBean deleteBrandByIds(@PathVariable Long[] ids) {
        try {
            System.out.println("删除品牌Id：");
            for (Long id : ids) {
                System.out.print(id + "\t");
            }
            brandService.deleteBrandByIds(ids);
            return ResultBean.ok("删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return ResultBean.error("删除失败");
        }
    }
}
