package com.zzx.service;

import com.github.pagehelper.PageInfo;
import com.zzx.domain.ProductInfo;
import com.zzx.vo.ProductInfoVo;
import org.omg.CORBA.Principal;

import java.util.List;

/**
 * @author ZZX
 * @date 2022/1/25 17:37
 */
public interface ProductInfoService {
    /**
     * 获取全部商品列表【未分页】
     * @return
     */
    List<ProductInfo> getAll();


    /**
     * 获取商品【分页】
     * select * from product_info limit (当前页-1 * 每页的条数) , 每页取几条
     * limit (pageNum-1*pageSize),pageSize
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo<ProductInfo>splitPage(int pageNum,int pageSize);

    /**
     * 商品上传
     * @param info
     * @return
     */
    int save(ProductInfo info);

    /**
     * 根据商品id查询商品信息
     * @param pId
     * @return
     */
    ProductInfo getById(Integer pId);

    /**
     * 更新商品
     * @param productInfo
     * @return
     */
    int update(ProductInfo productInfo);

    /**
     * 根据单个id删除商品
     * @param pId
     * @return
     */
    int delete(int pId);

    /**
     * 批量删除
     * @param ids
     * @return
     */
    int deleteBatch(String[] ids);

    /**
     * 根据条件查询
     * @param vo
     * @return
     */
    List<ProductInfo>selectCondition(ProductInfoVo vo);

    /**
     * 多条件查询分页
     * @param vo
     * @param pageSize
     * @return
     */
    PageInfo splitPageVo(ProductInfoVo vo,int pageSize);
}
