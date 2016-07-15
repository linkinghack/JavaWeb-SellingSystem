package com.linkinghack.SellingSystem.dao.mappers;

import java.util.List;

import com.linkinghack.SellingSystem.dao.ProductProvider;
import org.apache.ibatis.annotations.*;

import com.linkinghack.SellingSystem.meta.Product;
import org.springframework.stereotype.Repository;


public interface ProductMapper {
	
	@Select("SELECT * FROM content WHERE id=#{id}")
	public Product getProduct(int id);

	@Select("SELECT * FROM content ORDER BY id DESC LIMIT 1;")
	public Product getNewProduct();

	@Results({@Result (property="comment",column="comment"),
				@Result(property="id",column="id"),
				@Result(property="text",column="text"),
				@Result(property="icon",column="icon"),
				@Result(property="title",column="title"),
				@Result(property="price",column="price")})
	@Select("SELECT * FROM content")
	public List<Product> getProducts();

	@Select("SELECT * FROM trx WHERE id=#{contentId}")
	public List<Product> getProductsById(int contentId);

	@Delete("delete from content where id=#{id}")
	public void delete(int id);

	@InsertProvider(type = ProductProvider.class,method = "addProduct")
	public void addProduct(@Param("product") Product product);

	@UpdateProvider(type = ProductProvider.class,method = "updateProduct")
	public void updateProduct(@Param("product") Product product);
}
