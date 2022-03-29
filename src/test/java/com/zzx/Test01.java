package com.zzx;


import com.zzx.domain.ProductInfo;
import com.zzx.mapper.ProductInfoMapper;
import com.zzx.vo.ProductInfoVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author ZZX
 * @date 2022/1/25 17:37
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-dao.xml","classpath:spring-service.xml"})
public class Test01 {
    @Resource
    ProductInfoMapper mapper;

    @Test
    public void test(){
        ProductInfoVo infoVo=new ProductInfoVo();
        List<ProductInfo>list= mapper.selectCondition(infoVo);
        System.out.println(list.size());
    }
}
