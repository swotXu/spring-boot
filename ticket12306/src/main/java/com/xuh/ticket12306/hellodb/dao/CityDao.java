package com.xuh.ticket12306.hellodb.dao;

import com.xuh.ticket12306.hellodb.pojo.CityEntity;
import org.apache.ibatis.annotations.*;

@Mapper
public interface CityDao {

    @Select("SELECT id, name, countryCode, district, population FROM city WHERE id = #{id}")
    CityEntity selectCityById(@Param("id") int id);

    @Insert("INSERT INTO city(name, countryCode, district, population) " +
            "VALUES(#{name},#{countryCode},#{district},#{population})")
    int insert(CityEntity cityEntity);

    @Update("UPDATE city SET name=#{name},countryCode=#{countryCode},district=#{district},population=#{population} WHERE id=#{id}")
    int updateById(CityEntity cityEntity);
}
