package com.ssm.messgepush.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AppPushServiceTest {
	
    private static final Logger LOGGER = LoggerFactory.getLogger(AppPushServiceTest.class);
    
    @Autowired
    private AppPushService appPushService;
    
    @Test
    public void pushMessageToAll(){
    	
    	appPushService.pushMessageToAll();
    	
    }
    
    //@Test
    public void pushMessageToSingle(){
    	
    	appPushService.pushMessageToSingle();
    	
    }
    
    

}
