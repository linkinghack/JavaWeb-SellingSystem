package com.linkinghack.SellingSystem.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.linkinghack.SellingSystem.dao.mappers.PersonMapper;
import com.linkinghack.SellingSystem.dao.mappers.ProductMapper;
import com.linkinghack.SellingSystem.meta.Product;
import com.linkinghack.SellingSystem.meta.Trx;
import com.linkinghack.SellingSystem.meta.User;
import com.linkinghack.SellingSystem.service.BuyerService;
import com.linkinghack.SellingSystem.service.ProductService;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Linking on 2016/7/2.
 */

@Controller
public class RootController {
    @Autowired
    private ProductService productService;
    @Autowired
    private PersonMapper personMapper;
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private BuyerService buyerService;

    @RequestMapping("/")
    public String index(ModelMap map,HttpServletRequest request,HttpServletResponse response){
        List<Product> productList;
        User user;
        //Analize User dependency
        try {
                System.out.println("userName(From Session):"+request.getSession().getAttribute("userName"));

                user = personMapper.findUserByName((String) request.getSession().getAttribute("userName"));
                map.addAttribute("user", user);

        }catch (Exception e){
            e.printStackTrace();
        }
        //Analize product dependency
        productList = productService.listProduct();
        for (Product product:productList){
            System.out.println("Title: " + product.getTitle());
            System.out.println("Icon: " + product.getIcon());
            System.out.println("IsBuy: " + product.getIsBuy());
        }
        map.addAttribute("productList",productList);
        return "index";
    }
    
    @RequestMapping("/logout")
    public String logout(){
    	return "login";
    }
    
    @RequestMapping("/login")
    public String login(){
    	return "login";
    }

    @RequestMapping("/public")
    public String toPublic(HttpServletRequest request,ModelMap map){
        //Analize User dependency
        User user;
        try {
            user = personMapper.findUserByName((String) request.getSession().getAttribute("userName"));
            map.addAttribute("user", user);

        }catch (Exception e){
            e.printStackTrace();
        }

        return "public";

    }
    
    @RequestMapping("/show")
    public String show(ModelMap map,HttpServletRequest request){
        //Analize User dependency
        User user;
        try {
            user = personMapper.findUserByName((String) request.getSession().getAttribute("userName"));
            map.addAttribute("user", user);

        }catch (Exception e){
            e.printStackTrace();
        }

    	Product product = productService.showProduct(Integer.valueOf(request.getParameter("id")));
        map.addAttribute("product",product);
    	return "show";
    }

    @RequestMapping("/account")
    public String account(HttpServletRequest request,ModelMap map){
        //Analize User dependency
        User user;
        try {

            user = personMapper.findUserByName((String) request.getSession().getAttribute("userName"));
            map.addAttribute("user", user);

        }catch (Exception e){
            e.printStackTrace();
        }

        List<Trx> trxes = buyerService.getBuyList((String) request.getSession().getAttribute("userName"));
        map.addAttribute("buyList",trxes);
        return "account";
    }

    @RequestMapping("/settleAccount")
    public String settleAccount(HttpServletRequest request,ModelMap map){
        //Analize User dependency
        User user;
        try {

            user = personMapper.findUserByName((String) request.getSession().getAttribute("userName"));
            map.addAttribute("user", user);

        }catch (Exception e){
            e.printStackTrace();
        }

        return "settleAccount";
    }

    @RequestMapping(path = "/publicSubmit",method = RequestMethod.POST)
    public String publicSubmit(HttpServletRequest request,ModelMap map){
        Product product = new Product();

        try {
            product.setTitle(request.getParameter("title"));
            product.setText(request.getParameter("detail"));
            product.setPrice(Long.valueOf(request.getParameter("price")));
            product.setComment(request.getParameter("summary"));
            product.setIcon(request.getParameter("image"));

            productMapper.addProduct(product);
            //Get product info including id
            product = productMapper.getNewProduct();
            map.addAttribute("product",product);
        }catch (Exception e){
            e.printStackTrace();
        }

        return "/publicSubmit";
    }

    @RequestMapping("/edit")
    public String edit(HttpServletRequest request,ModelMap map){
        Product product = null;
        //Analize User dependency
        User user;
        try {
            System.out.println("userName(From Session):"+request.getSession().getAttribute("userName"));
            user = personMapper.findUserByName((String) request.getSession().getAttribute("userName"));
            map.addAttribute("user", user);

        }catch (Exception e){
            e.printStackTrace();
        }


        try {
            product = productMapper.getProduct(Integer.valueOf(request.getParameter("id")));
            map.addAttribute("product",product);
        }catch (Exception e){
            e.printStackTrace();
        }

        return "edit";
    }

    @RequestMapping(path = "/editSubmit",method = RequestMethod.POST)
    public String editSubmit(HttpServletRequest request,ModelMap map){
        Product product = null;
        try {
            product = productMapper.getProduct(Integer.valueOf(request.getParameter("id")));
            product.setTitle(request.getParameter("title"));
            product.setText(request.getParameter("detail"));
            product.setPrice(Long.valueOf(request.getParameter("price")));
            product.setComment(request.getParameter("summary"));
            product.setIcon(request.getParameter("image"));

            productMapper.updateProduct(product);
            map.addAttribute("product",product);
        }catch (Exception e){
            e.printStackTrace();
        }

        return "/editSubmit";
    }

}
