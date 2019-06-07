package com.mailbackstage.controller;

import com.mailbackstage.model.PmsBrand;
import com.mailbackstage.model.PmsBrandExample;
import com.mailbackstage.service.PmsBrandService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Yan liang
 * @create 2019/6/7
 * @since 1.0.0
 */
@Api(tags = "PmsBrandController", description = "商品品牌管理")
@RestController
@RequestMapping("PmsBrand")
public class PmsBrandController {
    @Autowired
    private PmsBrandService pmsBrandService;

    @GetMapping(value = "getList")
    @ApiOperation("获取商品品牌列表")
    public List<PmsBrand> getList() {
        return pmsBrandService.getList(new PmsBrandExample());
    }

}