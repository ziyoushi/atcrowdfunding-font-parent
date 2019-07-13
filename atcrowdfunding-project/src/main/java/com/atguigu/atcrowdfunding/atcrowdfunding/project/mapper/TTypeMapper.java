package com.atguigu.atcrowdfunding.atcrowdfunding.project.mapper;

import com.atguigu.atcrowdfunding.front.bean.TType;
import com.atguigu.atcrowdfunding.front.bean.TTypeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TTypeMapper {
    long countByExample(TTypeExample example);

    int deleteByExample(TTypeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TType record);

    int insertSelective(TType record);

    List<TType> selectByExample(TTypeExample example);

    TType selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TType record, @Param("example") TTypeExample example);

    int updateByExample(@Param("record") TType record, @Param("example") TTypeExample example);

    int updateByPrimaryKeySelective(TType record);

    int updateByPrimaryKey(TType record);
}