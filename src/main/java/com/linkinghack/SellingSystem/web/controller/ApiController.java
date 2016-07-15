package com.linkinghack.SellingSystem.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.linkinghack.SellingSystem.meta.*;
import com.linkinghack.SellingSystem.service.BuyerService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.linkinghack.SellingSystem.dao.JdbcTools;
import com.linkinghack.SellingSystem.dao.mappers.PersonMapper;
import com.linkinghack.SellingSystem.dao.mappers.ProductMapper;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/api")
public class ApiController {
	@Autowired
	private JdbcTools jdbcTools;
	@Autowired
	private ProductMapper productMapper;
    @Autowired
    private BuyerService buyerService;
	//Object to Json Tool
	private ObjectMapper objectMapper  = new ObjectMapper();

	//Check f logged. Replaced by RightsFilter
	private int isLogged(HttpServletRequest request){
		int type;
		String userName = (String)request.getSession().getAttribute("userName");
		String password = (String)request.getSession().getAttribute("password");
		if(jdbcTools.hasUser(userName,password)){
			type = jdbcTools.getUserType(userName);
		}else
			type = -1;

		return type;
	}

	@RequestMapping(path="/login",method=RequestMethod.POST)
	@ResponseBody
	public String login(HttpServletRequest request){
		HttpSession session = request.getSession();
		ApiStatus status; //To return json data
		String json=null;
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		//Check the userName and password
		System.out.println("userName(from POST): "+request.getParameter("userName"));
		System.out.println("Password(from POST): "+request.getParameter("password"));
		if(jdbcTools.hasUser(userName,password) ){

			session.setAttribute("userName", userName);
			session.setAttribute("password", password);

			System.out.println("userName(From Session-api):"+request.getSession().getAttribute("userName"));

			status = new ApiStatus(200,"Login done.",true);
		}else{
			status = new ApiStatus(401,"Failure",false);
		}

		try {
			//swich to json
			json = objectMapper.writeValueAsString(status);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			System.out.println("Cannot swich to json.");
		}
		
		return json;
	}
	
	@RequestMapping(path="/delete",method=RequestMethod.POST)
	@ResponseBody
	public String delete(HttpServletRequest request){
		ApiStatus status; //To return json data
		String json=null;
		String userName = (String) request.getSession().getAttribute("userName");

		//check if user is seller
		if(isLogged(request) == 1){
			productMapper.delete(Integer.parseInt(request.getParameter("id")));
			status = new ApiStatus(200,"Done",true);
		}else{
			status = new ApiStatus(401,"Error",false);
		}

		try {
			//swich to json
			json = objectMapper.writeValueAsString(status);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			System.out.println("Someing bad occured when swich to json.");
			System.out.println(e.getMessage());
		}
		return json;
	}


	@RequestMapping(path="/buy",method = RequestMethod.POST)
	@ResponseBody
	public String buy(HttpServletRequest request,@RequestBody List<CartProduct> buyList){
		ApiStatus status = new ApiStatus(401,"Something bad happened.",false);;
		String json=null;

		try {
			// buying service
                for (CartProduct product:buyList) {
					String userName = (String) request.getSession().getAttribute("userName");
					System.out.println("UserName (JDBCTOOLS):"+userName);
					System.out.println(jdbcTools.getUserId(userName));
					buyerService.buyProduct(jdbcTools.getUserId(userName),
							product.getId(), product.getNumber());
				}
                //set status
                status = new ApiStatus(200,"Buy Succcess",true);

		} catch (Exception e) {
			e.printStackTrace();

		}

        //Switch status to Json
		try {
			json = objectMapper.writeValueAsString(status);
		}catch (JsonProcessingException e){
			System.out.println("Someing bad occured when swich to json.");
			System.out.println(e.getMessage());
		}
		return json;
	}

	@RequestMapping("/upload")
	@ResponseBody
	public String upload(HttpServletRequest request ,@RequestParam("file") MultipartFile file){
		UploadStatus uploadStatus =  new UploadStatus(200,"ok","");
		String json=null;

		try{
			//Store the file
				String location = "../../image/";
				String fileName =  "" + new Date().getTime()+file.getOriginalFilename();
				String path = request.getServletContext().getRealPath("/")+"image/";

				file.transferTo(new File(path+fileName));

				uploadStatus.setCode(200);
				uploadStatus.setMessage("ok");
				uploadStatus.setResult("/image/"+fileName);

		} catch(Exception e){
				e.printStackTrace();
			}

		try {
			json = objectMapper.writeValueAsString(uploadStatus);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return json;
	}
}
