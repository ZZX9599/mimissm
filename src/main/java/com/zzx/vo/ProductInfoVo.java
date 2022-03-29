package com.zzx.vo;

/**
 * @author ZZX
 * @date 2022/1/25 17:37
 */
public class ProductInfoVo {
    //商品名称
    private String pName;
    //商品类型
    private Integer typeId;
    //商品最低价
    private Integer lPrice;
    //商品最高价
    private Integer hPrice;
    //页码
    private Integer page=1;

    public ProductInfoVo() {
    }

    @Override
    public String toString() {
        return "ProductInfoVo{" +
                "pName='" + pName + '\'' +
                ", typeId=" + typeId +
                ", lPrice=" + lPrice +
                ", hPrice=" + hPrice +
                ", page=" + page +
                '}';
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public Integer getlPrice() {
        return lPrice;
    }

    public void setlPrice(Integer lPrice) {
        this.lPrice = lPrice;
    }

    public Integer gethPrice() {
        return hPrice;
    }

    public void sethPrice(Integer hPrice) {
        this.hPrice = hPrice;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public ProductInfoVo(String pName, Integer typeId, Integer lPrice, Integer hPrice, Integer page) {
        this.pName = pName;
        this.typeId = typeId;
        this.lPrice = lPrice;
        this.hPrice = hPrice;
        this.page = page;
    }
}
