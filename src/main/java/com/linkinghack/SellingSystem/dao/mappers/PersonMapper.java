package com.linkinghack.SellingSystem.dao.mappers;

import org.apache.ibatis.annotations.Select;

import com.linkinghack.SellingSystem.meta.User;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
@Repository
public interface PersonMapper {
	@Select("SELECT userType,userName FROM person WHERE userName=#{userName}")
	public User findUserByName(String userName);

	@Select("SELECT id FROM person WHERE userName=#{userName}")
	public int getUserIdByName(String userName);

}
