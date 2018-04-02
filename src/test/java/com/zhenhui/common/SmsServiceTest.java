package com.zhenhui.common;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class SmsServiceTest extends TestCase {

    public SmsServiceTest(String testName ) {
        super( testName );
    }

    public static Test suite() {
        return new TestSuite( SmsServiceTest.class );
    }

    public void testSms() {
        assertTrue( true );
    }
}


