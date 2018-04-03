package com.zhenhui.common;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;

import static junit.framework.TestCase.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SmsServiceAutoConfiguration.class)
@TestPropertySource(locations = "classpath:test.properties")
public class SmsServiceTest {

    @Autowired
    private SmsService smsService;

    @Test
    public void testSms() throws Exception{

        SmsSendResult result = smsService.send("17036492138", 11L, new HashMap<>());
        assertTrue(result != null);

    }
}


