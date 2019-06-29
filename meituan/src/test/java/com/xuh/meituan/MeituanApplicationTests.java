package com.xuh.meituan;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MeituanApplicationTests {
	@Autowired
	RestTemplate restTemplate;

	@Test
	public void contextLoads() {
		String url = "http://localhost:8081/ticket/hello/world";
		String body = restTemplate.getForEntity(url, String.class).getBody();
		System.out.println("=============" + body);
	}

}
