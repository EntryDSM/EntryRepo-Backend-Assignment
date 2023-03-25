package com.practice.board;

import com.practice.board.global.security.jwt.JwtProperties;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
class BoardApplicationTests {

	@Autowired
	private JwtProperties jwtProperties;

	@Test
	public void contextLoad() throws Exception{
		System.out.println(jwtProperties);
	}

}