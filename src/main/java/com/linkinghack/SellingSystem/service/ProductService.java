package com.linkinghack.SellingSystem.service;

import com.linkinghack.SellingSystem.dao.mappers.PersonMapper;
import com.linkinghack.SellingSystem.dao.mappers.ProductMapper;
import com.linkinghack.SellingSystem.dao.mappers.TrxMapper;
import com.linkinghack.SellingSystem.meta.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by linking on 16-7-13.
 */
@Service
public class ProductService {
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private TrxMapper trxMapper;
    @Autowired
    private PersonMapper personMapper;

    public List<Product> listProduct(){
        List<Product> productList = productMapper.getProducts();
        try {
            for (Product product : productList) {
                //Check isBuy and isSell
                System.out.println("TRX:contentId: " + trxMapper.trxOut(product.getId()));

                if (trxMapper.trxOut(product.getId()) != null) {
                    product.setIsSell(true);
                    product.setIsBuy(true);
                } else {
                    product.setIsBuy(false);
                    product.setIsSell(false);
                }
            }
        }catch (Exception e){
            System.out.println("Failure occured when set isSell(isBuy).");
            e.printStackTrace();
        }
        return productList;
    }


    public Product showProduct(int contentId){
        Product product = null;
        try {
            product = productMapper.getProduct(contentId);
            long buyPrice = trxMapper.trxOut(contentId).get(0).getPrice();
            product.setBuyPrice(buyPrice);
            product.setBuyNum(trxMapper.trxOut(contentId).get(0).getNum());
            product.setSaleNum(trxMapper.trxOut(contentId).get(0).getNum());

            if (trxMapper.trxOut(product.getId()) != null) {
                product.setIsSell(true);
                product.setIsBuy(true);
            } else {
                product.setIsBuy(false);
                product.setIsSell(false);
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        return product;
    }

}
