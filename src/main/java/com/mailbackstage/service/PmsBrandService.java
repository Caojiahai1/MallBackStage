package com.mailbackstage.service;

import com.mailbackstage.model.PmsBrand;
import com.mailbackstage.model.PmsBrandExample;

import java.util.List;

/**
 * @author Yan liang
 * @create 2019/6/7
 * @since 1.0.0
 */
public interface PmsBrandService {
    List<PmsBrand> getList(PmsBrandExample example);
}