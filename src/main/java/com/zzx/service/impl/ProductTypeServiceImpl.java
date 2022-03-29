package com.zzx.service.impl;

import com.zzx.domain.ProductType;
import com.zzx.domain.ProductTypeExample;
import com.zzx.mapper.ProductTypeMapper;
import com.zzx.service.ProductTypeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author ZZX
 * @date 2022/1/25 17:37
 */
@Service
public class ProductTypeServiceImpl implements ProductTypeService {
    @Resource
    private ProductTypeMapper productTypeMapper;

    @Override
    public List<ProductType> getAll() {
        //不追加条件，就是查询所有
        return productTypeMapper.selectByExample(new ProductTypeExample());
    }
}
