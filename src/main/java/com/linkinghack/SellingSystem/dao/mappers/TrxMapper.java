package com.linkinghack.SellingSystem.dao.mappers;

import com.linkinghack.SellingSystem.meta.Trx;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by linking on 16-7-11.
 */

@Repository
public interface TrxMapper {

    @Insert("INSERT INTO trx(personId,contentId,price,time,num) VALUES(#{0},#{1},#{2},#{3},#{4})")
    public int trxIn(int personId,int contentId,long price,long time,int num);

    @Select("SELECT * FROM trx WHERE contentId=#{contentId}")
    public List<Trx> trxOut(int contentId);

    @Select("SELECT * FROM trx WHERE personId=#{personId}")
    public List<Trx> trxOutByPersonId(int personId);

//    @Select("SELECT id FROM trx WHERE contentId=#{contentId}")
//    public Integer[] hasTrx(int contentId);

    @Select("SELECT userName FROM trx WHERE contentId=#{id}")
    public String[] getUserNameByContentId(int id);
}
