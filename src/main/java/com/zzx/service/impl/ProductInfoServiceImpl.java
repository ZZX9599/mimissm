package com.zzx.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zzx.domain.ProductInfo;
import com.zzx.domain.ProductInfoExample;
import com.zzx.mapper.ProductInfoMapper;
import com.zzx.service.ProductInfoService;
import com.zzx.vo.ProductInfoVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author ZZX
 * @date 2022/1/25 17:37
 */
@Service
public class ProductInfoServiceImpl implements ProductInfoService {
    @Resource
    private ProductInfoMapper productInfoMapper;

    @Override
    public List<ProductInfo> getAll() {
        //不带条件的话，默认就是查所有
        return productInfoMapper.selectByExample(new ProductInfoExample());
    }

    @Override
    public PageInfo<ProductInfo> splitPage(int pageNum,int pageSize) {
        //使用分页插件PageHelper工具类完成分页设置
        PageHelper.startPage(pageNum,pageSize);

        //进行PageInfo的数据封装
        //进行有条件的查询
        ProductInfoExample example=new ProductInfoExample();
        //设置排序,按主键降序排序
        //select * from product_info order by p_id desc
        example.setOrderByClause("p_id desc");

        //完成排序后，拿到集合【在拿到集合之前，一定要先设置pageNum,pageSize】
        List<ProductInfo>splitList=productInfoMapper.selectByExample(example);

        //把查询到的集合，封装到PageInfo对象
        PageInfo<ProductInfo>productInfoPageInfo=new PageInfo<>(splitList);
        return productInfoPageInfo;
    }

    @Override
    public int save(ProductInfo info) {
        return productInfoMapper.insert(info);
    }

    @Override
    public ProductInfo getById(Integer pId) {
        return productInfoMapper.selectByPrimaryKey(pId);
    }

    @Override
    public int update(ProductInfo productInfo) {
        //根据主键id进行更新
        productInfo.setpDate(new Date());
        return productInfoMapper.updateByPrimaryKey(productInfo);
    }

    @Override
    public int delete(int pId) {
        return productInfoMapper.deleteByPrimaryKey(pId);
    }

    @Override
    public PageInfo<ProductInfo> splitPageVo(ProductInfoVo vo, int pageSize) {
        //取得集合之前，设置PageHelper.startPage()属性
        //开始页和大小
        PageHelper.startPage(vo.getPage(),pageSize);
        List<ProductInfo>list=productInfoMapper.selectCondition(vo);
        return new PageInfo<>(list);
    }

    @Override
    public List<ProductInfo> selectCondition(ProductInfoVo vo) {
        return productInfoMapper.selectCondition(vo);
    }

    @Override
    public int deleteBatch(String[] ids) {
        return productInfoMapper.deleteBatch(ids);
    }
}
