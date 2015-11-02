package com.brittonn.mvcpract.junit.rest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ FoodItemServiceTest.class, LoginServiceTest.class, MySecurityProviderTest.class, AuthorizationRequestFilterTest.class })
public class AllTests {

}
