package com.xuh.ticket12306.hellodb.service;

import com.xuh.ticket12306.hellodb.dao.CityDao;
import com.xuh.ticket12306.hellodb.pojo.CityEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CityService {
    @Autowired
    CityDao cityDao;

    public CityEntity selectCityById(int id){
        return cityDao.selectCityById(id);
    }
}
