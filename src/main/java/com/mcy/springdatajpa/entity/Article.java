package com.mcy.springdatajpa.entity;

import javax.persistence.*;
import java.util.Date;

//用于标记持久化类，SpringBoot项目加载后会自动根据持久化类建表
@Entity
//设置表名为tb_article
@Table(name="tb_article")
public class Article {
    /**
     * 使用@id指定主键。使用代码@GeneratedValue(strategy = GenerationType.IDENTITY)
     * 指定主键的生存策略，mysql默认为自动增长
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; //主键
    private String title;   //名称
    private String supplier;    //作者
    private Double price;   //单价
    private String locality;    //出版社
    private int storage;        //库存
    private String image;   //商品图片
    private String description; //商品描述
    private Date createDate;    //出版时间

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public int getStorage() {
        return storage;
    }

    public void setStorage(int storage) {
        this.storage = storage;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
