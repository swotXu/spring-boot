package com.xuh.ticket12306;

import com.xuh.ticket12306.hellodb.dao.CityDao;
import com.xuh.ticket12306.hellodb.pojo.CityEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Ticket12306ApplicationTests {
	@Autowired
	CityDao cityDao;
	@Test
	@Transactional
	public void contextLoads() {
//		CityEntity ce = new CityEntity(){
//			{
//				this.setId(4080);
//				this.setName("Name3");
//				this.setCountryCode("PSE");
//				this.setDistrict("District3");
//				this.setPopulation(643800);
//			}
//		};
//		int insert = cityDao.insert(ce);
//		System.out.println(insert);
//
//		int ud = cityDao.updateById(ce);
//		System.out.println(ud);

		CityEntity entity = cityDao.selectCityById(4082);
		System.out.println(entity);
	}

}
