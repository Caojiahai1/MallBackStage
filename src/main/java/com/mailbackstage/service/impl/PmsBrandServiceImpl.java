package com.mailbackstage.service.impl;

import com.github.pagehelper.PageHelper;
import com.mailbackstage.dao.PmsBrandMapper;
import com.mailbackstage.model.PmsBrand;
import com.mailbackstage.model.PmsBrandExample;
import com.mailbackstage.service.PmsBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Yan liang
 * @create 2019/6/7
 * @since 1.0.0
 */
@Service
public class PmsBrandServiceImpl implements PmsBrandService {
    @Autowired
    private PmsBrandMapper pmsBrandMapper;


    @Override
    public List<PmsBrand> getList(PmsBrandExample example) {
        PageHelper.startPage(0,5);
        return pmsBrandMapper.selectByExample(example);
    }
}