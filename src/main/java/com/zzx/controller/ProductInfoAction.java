package com.zzx.controller;

import com.github.pagehelper.PageInfo;
import com.zzx.domain.ProductInfo;
import com.zzx.service.ProductInfoService;
import com.zzx.utils.FileNameUtil;
import com.zzx.vo.ProductInfoVo;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * @author ZZX
 * @date 2022/1/25 17:37
 */
@Controller
@RequestMapping("/prod")
public class ProductInfoAction {
    @Resource
    private ProductInfoService productInfoService;
    /**
     * 每页的数据量
     */
    private static final int PAGE_SIZE=5;
    /**
     * 异步上传图片名
     */
    private String saveFileName="";

    @RequestMapping("/getAll")
    public String getAll(HttpServletRequest request){
        List<ProductInfo>productInfoList=productInfoService.getAll();
        request.setAttribute("productInfoList",productInfoList);
        return "product";
    }

    @RequestMapping("/split")
    public String split(HttpServletRequest request){
        PageInfo info=null;
        Object vo=request.getSession().getAttribute("vo");
        if(vo!=null){
            info=productInfoService.splitPageVo((ProductInfoVo) vo,PAGE_SIZE);
            request.getSession().removeAttribute("vo");
        }else {
            //拿到第一页数据
            info = productInfoService.splitPage(1, PAGE_SIZE);
            request.setAttribute("info", info);
        }
        return "product";
    }

    @ResponseBody
    @RequestMapping("/ajaxsplit")
    public void ajaxSplit(int page, HttpSession session){
        //取得当前的page参数的页面
        //这里用session，就不会取到request的前五条数据了
        PageInfo<ProductInfo>info=productInfoService.splitPage(page,PAGE_SIZE);
        session.setAttribute("info",info);
    }

    @ResponseBody
    @RequestMapping("/ajaxImg")
    public Object ajaxImg(MultipartFile pimage,HttpServletRequest request){
        //注意：MultipartFile的对象名，必须跟file控件的name一样
        //<input type="file" id="pimage" name="pimage" onchange="fileChange()">

        //得到存储的真实路径
        String path=request.getServletContext().getRealPath("/image_big");
        //System.out.println(path);

        //提取生成文件名 uuid+后缀
        saveFileName= FileNameUtil.getUUIDFileName()+
                FileNameUtil.getFileType(pimage.getOriginalFilename());
        //System.out.println(saveFileName);
        //System.out.println(File.separator);

        //转存
        try {
            pimage.transferTo(new File(path+File.separator+saveFileName));
        } catch (IOException e) {
            e.printStackTrace();
        }

        JSONObject object=new JSONObject();
        object.put("imgUrl",saveFileName);
        return object.toString();
    }

    @RequestMapping("/save")
    public String save(ProductInfo info,HttpServletRequest request){
        //从表单提交过来，差img和date
        info.setpImage(saveFileName);
        //填充日期
        info.setpDate(new Date());
        int result=0;
        try {
            result=productInfoService.save(info);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(result>0){
            request.setAttribute("msg","商品上传成功！");
        }else {
            request.setAttribute("msg","商品上传失败！");
        }
        //增加成功之后，置空，便于修改！
        saveFileName="";
        return "forward:/prod/split.action";
    }

    @RequestMapping("/getOne")
    public String getOne(int pid,ProductInfoVo vo, Model model,HttpSession session){
        //把条件存入Session
        session.setAttribute("vo",vo);
        ProductInfo info=productInfoService.getById(pid);
        model.addAttribute("prod",info);
        return "update";
    }

    @RequestMapping("/update")
    public String update(ProductInfo productInfo, HttpServletRequest request, HttpServletResponse response){
        //如果ajax的异步图片上传过的话，则saveFileName不为空
        //如果修改的时候，没有上传图片，则saveFileName为空
        /**
         * 如果saveFileName=="",就使用hidden表单提交原始数据
         * 如果saveFileName!="",提交新的图片
         */
        int result=0;
        if(saveFileName.length()!=0){
            productInfo.setpImage(saveFileName);
        }
        try {
            result=productInfoService.update(productInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(result>0){
            //更新成功
            request.setAttribute("msg","更新成功");
            //跳转到productinfo.jsp
        }else {
            request.setAttribute("msg","更新失败");
        }
        //为了以后上传。更新，这里还需要置空
        saveFileName="";
        //跳转到分页显示的页面
        return "forward:/prod/split.action";
    }

    @RequestMapping("/delete")
    public void delete(int pId,ProductInfoVo vo,HttpServletResponse response,HttpServletRequest request){
        response.setCharacterEncoding("UTF-8");
        int result=0;
        try {
            result=productInfoService.delete(pId);
            if(result>0){
                HttpSession session=request.getSession();
                session.setAttribute("deleteVo",vo);
                PageInfo<ProductInfo>info=productInfoService.splitPageVo(vo,PAGE_SIZE);
                session.setAttribute("info",info);
                response.getWriter().println("删除成功");
            }else {
                response.getWriter().println("删除失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/deleteBatch")
    public void deleteBatch(String pids,HttpServletResponse response,HttpServletRequest request){
        response.setCharacterEncoding("UTF-8");
        //参数中的pids实际上是字符串："1,2,3,4,5"
        //我们将它转化成["1","2","3","4","5"]
        //将字符串转化成数组
        String []ps=pids.split(",");
        int result=0;
        try {
            result=productInfoService.deleteBatch(ps);
            HttpSession session=request.getSession();
            PageInfo<ProductInfo>info=productInfoService.splitPage(1,PAGE_SIZE);
            session.setAttribute("info",info);
            if(result>0){
                response.getWriter().println("批量删除成功");
            }else {
                response.getWriter().println("不可删除商品介绍项");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @ResponseBody
    @RequestMapping("/condition")
    public void Condition(ProductInfoVo vo,HttpSession session){
        vo.setPage(1);
        PageInfo<ProductInfoVo>info=productInfoService.splitPageVo(vo,PAGE_SIZE);
        session.setAttribute("info",info);
    }

    @ResponseBody
    @RequestMapping("/splitCondition")
    public void selectCondition(ProductInfoVo vo,HttpSession session){
        PageInfo<ProductInfoVo>info=productInfoService.splitPageVo(vo,PAGE_SIZE);
        session.setAttribute("info",info);
    }
}
