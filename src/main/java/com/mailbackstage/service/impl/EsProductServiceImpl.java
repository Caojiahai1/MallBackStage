package com.mailbackstage.service.impl;

import com.mailbackstage.dao.EsProductDao;
import com.mailbackstage.nosql.elasticsearch.document.EsProduct;
import com.mailbackstage.nosql.elasticsearch.repository.EsProductRepository;
import com.mailbackstage.service.EsProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Yan liang
 * @create 2019/6/9
 * @since 1.0.0
 */
@Service
public class EsProductServiceImpl implements EsProductService{
    @Autowired
    private EsProductDao esProductDao;
    @Autowired
    private EsProductRepository esProductRepository;

    @Override
    public int importAll() {
        List<EsProduct> esProductList = esProductDao.getAllEsProductList(null);
        Iterable<EsProduct> esProductIterable = esProductRepository.saveAll(esProductList);
        Iterator<EsProduct> iterator = esProductIterable.iterator();
        int result = 0;
        while (iterator.hasNext()) {
            result++;
        }
        return result;
    }

    @Override
    public void delete(Long id) {
        esProductRepository.deleteById(id);
    }

    @Override
    public EsProduct create(Long id) {
        EsProduct esProductResult = null;
        List<EsProduct> esProductList = esProductDao.getAllEsProductList(id);
        if (esProductList != null && esProductList.size() > 0) {
            EsProduct esProduct = esProductList.get(0);
            esProductResult = esProductRepository.save(esProduct);
        }
        return esProductResult;
    }

    @Override
    public void delete(List<Long> ids) {
        if (!CollectionUtils.isEmpty(ids)) {
            List<EsProduct> esProductList = new ArrayList<>();
            for (Long id : ids) {
                EsProduct esProduct = new EsProduct();
                esProduct.setId(id);
                esProductList.add(esProduct);
            }
            esProductRepository.deleteAll(esProductList);
        }
    }

    @Override
    public Page<EsProduct> search(String keyword, Integer pageNum, Integer pageSize) {
        Pageable page = PageRequest.of(pageNum, pageSize);
        return esProductRepository.findByNameOrSubTitleOrKeywords(keyword, keyword, keyword, page);
    }
}