package com.mailbackstage.controller;

import com.mailbackstage.common.CommonResult;
import com.mailbackstage.nosql.elasticsearch.document.EsProduct;
import com.mailbackstage.service.EsProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Yan liang
 * @create 2019/6/9
 * @since 1.0.0
 */
@Api(tags = "EsProductController", description = "搜索商品管理")
@RestController
@RequestMapping("/esProduct")
public class EsProductController {
    @Autowired
    private EsProductService esProductService;

    @ApiOperation("导入所有数据库中商品到ES")
    @GetMapping("/importAll")
    public CommonResult<Integer> importAll() {
        int count = esProductService.importAll();
        return new CommonResult<Integer>().success(true, "导入成功", count);
    }

    @ApiOperation("根据id删除商品")
    @DeleteMapping("/delete/{id}")
    public CommonResult delete(@PathVariable("id") Long id) {
        esProductService.delete(id);
        return new CommonResult().success(true, "删除成功");
    }

    @ApiOperation("批量删除商品")
    @DeleteMapping("/deleteByIds")
    public CommonResult deleteByIds(@RequestParam("ids") List<Long> ids) {
        esProductService.delete(ids);
        return new CommonResult().success(true, "批量删除成功");
    }

    @ApiOperation("根据id将数据库商品创建到es")
    @PostMapping("/create/{id}")
    public CommonResult<EsProduct> create(@PathVariable("id") Long id) {
        EsProduct esProduct = esProductService.create(id);
        if (esProduct == null) {
            return new CommonResult<>().success(false, "创建失败");
        } else {
            return new CommonResult<EsProduct>().success(true, "创建成功", esProduct);
        }
    }

    @ApiOperation("简单搜索")
    @GetMapping("/search")
    public CommonResult search(@RequestParam("keyword") String keyword,
                               @RequestParam(value = "pageNum", required = false, defaultValue = "0") int pageNum,
                               @RequestParam(value = "pageSize", required = false, defaultValue = "5") int pageSize) {
        Page<EsProduct> esProductPage = esProductService.search(keyword, pageNum, pageSize);
        List<EsProduct> esProductList = esProductPage.getContent();
        return new CommonResult<List<EsProduct>>().success(true, "操作成功", esProductList);
    }
}
