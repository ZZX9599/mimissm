package com.zzx.service;

import com.zzx.domain.ProductType;

import java.util.List;

/**
 * @author ZZX
 * @date 2022/1/25 17:37
 */
public interface ProductTypeService {
    /**
     * 查询所有商品类别
     * @return
     */
    List<ProductType> getAll();
}
