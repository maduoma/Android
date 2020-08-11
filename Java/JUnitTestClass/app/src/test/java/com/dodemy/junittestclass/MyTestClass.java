package com.dodemy.junittestclass;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class MyTestClass {


    @BeforeClass
    public static void beforeClassMethod() throws Exception { }

    @Test
    public void test1() throws Exception { }

    @Before
    public void beforeMethod() throws Exception { }

    @Test
    public void test2() throws Exception { }

    @After
    public void afterMethod() throws Exception { }

    @Test
    public void test3() throws Exception { }

    @AfterClass
    public static void afterClassMethod() throws Exception { }

}
