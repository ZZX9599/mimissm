package com.zzx.listener;

import com.zzx.domain.ProductType;
import com.zzx.service.ProductInfoService;
import com.zzx.service.ProductTypeService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.List;

/**
 * @author ZZX
 * @date 2022/1/25 17:37
 * ServletContextListener 全局监听器
 */
@WebListener
public class ProductTypeListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        //手工从spring容器中拿到ProductServiceImpl对象
        String path="spring-*.xml";
        ApplicationContext context=new ClassPathXmlApplicationContext(path);
        ProductTypeService service=(ProductTypeService)context.getBean("productTypeServiceImpl");
        //获取所有的商品类别
        List<ProductType>typeList=service.getAll();
        //放入全局作用域对象，给增加，修改填充商品类别
        servletContextEvent.getServletContext().setAttribute("typeList",typeList);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        System.out.println("全局监听器销毁！");
    }
}
