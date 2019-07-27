package com.ljl.dao;

import com.ljl.pojo.Autologin;
import com.ljl.pojo.AutologinExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

public interface AutologinMapper {
    int countByExample(AutologinExample example);

    int deleteByExample(AutologinExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Autologin record);

    int insertSelective(Autologin record);

    List<Autologin> selectByExample(AutologinExample example);

    Autologin selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Autologin record, @Param("example") AutologinExample example);

    int updateByExample(@Param("record") Autologin record, @Param("example") AutologinExample example);

    int updateByPrimaryKeySelective(Autologin record);

    int updateByPrimaryKey(Autologin record);
}