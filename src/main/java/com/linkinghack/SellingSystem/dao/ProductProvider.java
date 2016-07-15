package com.linkinghack.SellingSystem.dao;

import static org.apache.ibatis.jdbc.SqlBuilder.*;

/**
 * Created by linking on 16-7-14.
 */
public class ProductProvider {
    public String addProduct(){
        BEGIN();
        INSERT_INTO("content");
        VALUES("price,title,icon,comment,text","#{product.price},#{product.title},#{product.icon},#{product.comment}," +
                "#{product.text}");

        return SQL();
    }

    public String updateProduct(){
        BEGIN();
        UPDATE("content");
        SET("price=#{product.price},title=#{product.title},icon=#{product.icon},comment=#{product.comment}," +
                "text=#{product.text}");
        WHERE("id=#{product.id}");
        return SQL();
    }
}
